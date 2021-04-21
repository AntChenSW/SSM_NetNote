package cn.antchensw.netnote.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: Ant_Chen
 * @version：2020年6月25日 上午9:17:44
 */
@Controller
public class UrlController {

    @RequestMapping("/login")
    public String toLogin(HttpSession session) {
        return "login";
    }

    @RequestMapping("/register")
    public String toRegister() {
        return "register";
    }

    @RequestMapping("/getpass")
    public String toGetPass() {
        return "getpass";
    }

    @RequestMapping("/userinfo")
    public String toUserInfo() {
        return "userinfo";
    }

    @RequestMapping("/note")
    public String toNote() {
        return "note";
    }

}
