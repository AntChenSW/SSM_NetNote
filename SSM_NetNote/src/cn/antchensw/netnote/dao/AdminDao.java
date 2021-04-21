package cn.antchensw.netnote.dao;

/** 
* @author: Ant_Chen
* @version：2020年6月7日 上午9:35:12 
*/

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.antchensw.netnote.bean.Admin;
import cn.antchensw.netnote.bean.User;

public interface AdminDao {

    public Admin getAdminByName(String name) throws SQLException;

    public Admin getAdmin(@Param("name") String name, @Param("password") String password) throws SQLException;

    public List<User> getAllUsers() throws SQLException;

}
