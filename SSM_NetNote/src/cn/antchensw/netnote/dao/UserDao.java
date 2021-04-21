package cn.antchensw.netnote.dao;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Param;

import cn.antchensw.netnote.bean.User;

/**
 * @author: Ant_Chen
 * @version：2020年6月6日 下午2:53:27
 */
public interface UserDao {

    // 查询是否存在某个用户
    public User getUserByName(String name) throws SQLException;

    public User getUserByEmail(String email) throws SQLException;

    // 获取用户(登录)
    public User getUser(@Param("name") String name, @Param("password") String password) throws SQLException;

    public int addUser(User user) throws SQLException;

    public int delUser(int id) throws SQLException;

    public int updateUser(User user) throws SQLException;

    public String getPass(String email) throws SQLException;

}
