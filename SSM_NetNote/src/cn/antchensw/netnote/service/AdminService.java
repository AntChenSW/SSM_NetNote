package cn.antchensw.netnote.service;

import java.sql.SQLException;
import java.util.List;

import cn.antchensw.netnote.bean.Admin;
import cn.antchensw.netnote.bean.User;

/**
 * @author: Ant_Chen
 * @version：2020年6月24日 下午10:51:00
 */
public interface AdminService {

    public boolean getAdminByName(String name) throws SQLException;

    public Admin getAdmin(String name, String password) throws SQLException;

    public List<User> getAllUsers() throws SQLException;

}
