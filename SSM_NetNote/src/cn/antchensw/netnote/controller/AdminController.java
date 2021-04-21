package cn.antchensw.netnote.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.antchensw.netnote.bean.Admin;
import cn.antchensw.netnote.bean.User;
import cn.antchensw.netnote.service.AdminService;
import cn.antchensw.netnote.service.UserService;

/**
 * @author: Ant_Chen
 * @version：2020年6月25日 上午11:42:04
 */
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @RequestMapping("/admin")
    public String toAdmin(HttpSession session) throws SQLException {
        List<User> users = adminService.getAllUsers();
        session.setAttribute("users_session", users);
        return "admin";
    }

    @RequestMapping(value = "/admin.login", method = RequestMethod.POST)
    public String login(String name, String password, HttpSession session) {
        Admin admin = null;
        try {
            if (adminService.getAdminByName(name)) {
                // 管理员账号 正确
                if ((admin = adminService.getAdmin(name, password)) != null) {
                    // 管理员账号密码 均正确
                    session.setAttribute("admin_session", admin);
                    session.setAttribute("msg_login", "登录成功!");
                    return "redirect:admin";
                } else {
                    // 管理员账号正确,但密码错误
                    session.setAttribute("msg", "请输入正确的管理员密码!");
                }
            } else {
                // 管理员账号错误
                session.setAttribute("msg", "请输入正确的管理员账号!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "redirect:login";
    }

    @RequestMapping("/admin.deluser")
    public String delUser(String user_id, HttpSession session) {
        try {
            if (userService.delUser(Integer.parseInt(user_id))) {
                session.setAttribute("msg_login", "删除用户" + user_id + "成功！");
            } else {
                session.setAttribute("msg_login", "删除用户" + user_id + "失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("msg_login", "删除用户" + user_id + "异常,请稍后再试！");
        }
        return "redirect:admin";

    }

    @RequestMapping("/admin.logout")
    public String logout(HttpSession session, HttpServletRequest request) {
        session.invalidate();
        request.setAttribute("msg", "管理员已退出登录!");
        return "login";
    }

}
