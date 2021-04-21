package cn.antchensw.netnote.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.antchensw.netnote.bean.Admin;
import cn.antchensw.netnote.bean.User;

/**
 * @author: Ant_Chen
 * @version：2020年6月25日 上午9:32:37
 */
public class LoginIntercepter implements HandlerInterceptor {

    private String passPath = "/login/getpass/register"
            + "/user.login/admin.login/user.register/user.imagecode/user.getpass";
    private Admin admin = null;
    private User user = null;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object,
            Exception model) throws Exception {
        // TODO

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model)
            throws Exception {
        // TODO

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        // TODO
        String path = request.getServletPath();
        HttpSession session = request.getSession();
        System.out.println("请求的地址为:" + path);

        admin = (Admin) session.getAttribute("admin_session");
        user = (User) session.getAttribute("user_session");
        if (passPath.contains(path)) {
            return true;
        } else {
            if (user == null && admin == null) {
                request.setAttribute("msg", "请您先登录!");
                request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
                return false;
            } else {
                return true;
            }
        }
    }

}
