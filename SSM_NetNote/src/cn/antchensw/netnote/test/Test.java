package cn.antchensw.netnote.test;

import java.sql.SQLException;

import cn.antchensw.netnote.bean.Admin;
import cn.antchensw.netnote.service.AdminService;
import cn.antchensw.netnote.service.impl.AdminServiceImpl;

/**
 * @author: Ant_Chen
 * @version：2020年6月7日 上午10:28:18
 */
public class Test {

    public Test() {
    }

    public static void main(String[] args) throws SQLException {
        // TODO
        AdminService adminService = new AdminServiceImpl();
        Admin admin = adminService.getAdmin("a", "1");
        System.out.println(admin.toString());

    }

}
