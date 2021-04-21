package cn.antchensw.netnote.service;

import java.sql.SQLException;

import cn.antchensw.netnote.bean.User;

/**
 * @author: Ant_Chen
 * @version：2020年6月24日 下午10:52:45
 */
public interface UserService {

    // 查询是否存在某个用户
    public boolean getUserByName(String name) throws SQLException;

    public boolean getUserByEmail(String email) throws SQLException;

    // 获取用户(登录)
    public User getUser(String name, String password) throws SQLException;

    public boolean addUser(User user) throws SQLException;

    public boolean delUser(int id) throws SQLException;

    public boolean updateUser(User user) throws SQLException;

    public String getPass(String email) throws SQLException;
}
