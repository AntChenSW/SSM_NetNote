package cn.antchensw.netnote.controller;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.antchensw.netnote.bean.Note;
import cn.antchensw.netnote.bean.User;
import cn.antchensw.netnote.bean.VerificationCode;
import cn.antchensw.netnote.service.NoteService;
import cn.antchensw.netnote.service.UserService;
import cn.antchensw.netnote.util.CreateVerificationCodeImage;
import cn.antchensw.netnote.util.Tools;

/**
 * @author: Ant_Chen
 * @version：2020年6月24日 下午11:18:11
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private NoteService noteService;

    @RequestMapping("/Test")
    public String Test(Model model) throws SQLException {
        User user = userService.getUser("user", "123456");
        System.out.println(user.toString());
        System.out.println(userService.getUserByEmail("1553739449@qq.com"));
        System.out.println(userService.getPass("1553739449@qq.com"));
        model.addAttribute("user", user);
        return "test";
    }

    @RequestMapping("/index")
    public String toIndex(HttpSession session) throws SQLException {
        User user = (User) session.getAttribute("user_session");
        List<Note> notes = noteService.getNotes(user.getId());
        session.setAttribute("notes", notes);
        return "index";
    }

    @RequestMapping("/user.register")
    public String registerUser(String name, String email, String password1, HttpSession session) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password1);
        session.setAttribute("user_register_session", user);
        try {
            if (userService.getUserByName(name)) {
                session.setAttribute("msg_register", "已存在该用户名" + name + "，请修改！");
                return "redirect:register";
            }
            if (userService.getUserByEmail(email)) {
                session.setAttribute("msg_register", "已存在该邮箱" + email + "，请修改！");
                return "redirect:register";
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        try {
            if (userService.addUser(user)) {
                session.setAttribute("msg", "用户" + name + "注册成功！");
                session.removeAttribute("user_register_session");
                return "redirect:login";
            } else {
                session.setAttribute("msg_register", "注册失败，请联系管理员！");
                return "redirect:register";
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("msg_register", "注册异常，请稍后再试！");
            return "redirect:register";
        }

    }

    @RequestMapping("/user.uploadphoto")
    public String uploadPhoto(@RequestParam("photofile") MultipartFile photo, HttpSession request, HttpSession session)
            throws SQLException {
        if (photo.getSize() > 1048576) {
            session.setAttribute("msg_savephoto", "上传失败:图片格式必须在1M以内!");
            return "userinfo";
        }
        String pass = ".jpg.png";
        String suffix = photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".") + 1);
        System.out.println(suffix);
        if (pass.contains(suffix)) {
            User user = (User) session.getAttribute("user_session");
            String dirPath = request.getServletContext().getRealPath("/img/");
            File filePath = new File(dirPath);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            try {
                photo.transferTo(new File(dirPath + user.getId() + ".png"));
                session.setAttribute("msg_savephoto", "头像上传成功!");
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                session.setAttribute("msg_savephoto", "头像上传异常,请刷新后再试!");
                return "redirect:userinfo";
            }
            user.setPhoto(1);
            userService.updateUser(user);
        } else {
            session.setAttribute("msg_savephoto", "上传失败:图片文件必须为JPG或者PNG格式!");
        }
        return "redirect:userinfo";
    }

    @RequestMapping("user.downloadphoto")
    public ResponseEntity<byte[]> downloadPhoto(HttpServletRequest request, String photoname) throws Exception {
        String path = request.getServletContext().getRealPath("/img/");
        File file = new File(path + File.separator + photoname);
        photoname = Tools.getFileName(request, photoname);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attchment", photoname);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
    }

    @RequestMapping("/user.updateusername")
    public String updateUsername(@RequestParam("name") String newName, HttpSession session) {
        User user = (User) session.getAttribute("user_session");
        System.out.println(newName);
        try {
            if (!userService.getUserByName(newName)) {
                user.setName(newName);
                if (userService.updateUser(user)) {
                    session.removeAttribute("user_session");
                    session.setAttribute("user_session", user);
                    session.setAttribute("msg_update", "用户名修改成功!");
                } else {
                    session.setAttribute("msg_update", "用户名修改失败!");
                }
            } else {
                session.setAttribute("msg_update", "用户名" + newName + "已被使用!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "redirect:userinfo";
    }

    @RequestMapping("/user.imagecode")
    public void getImageCode(HttpSession session, HttpServletResponse response) throws Exception {
        // TODO Auto-generated method stub
        String vericode = VerificationCode.getSecurityCode();
        session.setAttribute("verityCode", vericode);
        // 设置返回的内容
        response.setContentType("img/jpeg");
        // 浏览器不缓存响应内容--验证码图片，点一次就要刷新一次，所以不能有缓存出现
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        // 设置验证码失效时间
        response.setDateHeader("Expires", 0);
        // 以字节流发过去，交给img的src属性去解析即可
        ImageIO.write(new CreateVerificationCodeImage(vericode).createImage(), "JPEG", response.getOutputStream());
    }

    @RequestMapping("/user.getpass")
    public String getPass(String email, String vericode, HttpSession session) {
        String verityCode_session = (String) session.getAttribute("verityCode");
        if (vericode.equals(verityCode_session)) {
            try {
                if (userService.getUserByEmail(email)) {
                    String pass = userService.getPass(email);
                    if (Tools.sendEmail(email, "网络笔记-找回密码", "请不要泄露邮件内容,您的密码为:" + pass)) {
                        session.setAttribute("msg_pass", "密码已发送至" + email + "，请查收邮件！");
                    } else {
                        session.setAttribute("msg_pass", "网络错误,邮件发送失败！");
                    }
                } else {
                    session.setAttribute("msg_pass", "邮箱" + email + "尚未注册！");
                }
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                session.setAttribute("msg_pass", "获取密码异常！");
            }
        } else {
            session.setAttribute("email_session", email);
            session.setAttribute("msg_pass", "验证码有误！");
        }
        return "redirect:getpass";
    }

    @RequestMapping(value = "/user.login", method = RequestMethod.POST)
    public String login(String name, String password, HttpSession session) {
        User user = null;
        try {
            if (userService.getUserByName(name)) {
                if ((user = userService.getUser(name, password)) != null) {
                    session.setAttribute("user_session", user);
                    session.setAttribute("msg_login", "登录成功!");
                    return "redirect:index";
                } else {
                    // 用户账号正确,但密码错误
                    session.setAttribute("msg", "请输入正确的用户密码!");
                }
            } else {
                // 用户账号错误
                session.setAttribute("msg", "请输入正确的用户账号!");
            }
            System.out.println("用户登录失败!");
            return "redirect:login";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "redirect:login";
    }

    @RequestMapping("/user.logout")
    public String logout(HttpSession session, HttpServletRequest request) {
        session.invalidate();
        request.setAttribute("msg", "用户已退出登录!");
        return "login";
    }

}
