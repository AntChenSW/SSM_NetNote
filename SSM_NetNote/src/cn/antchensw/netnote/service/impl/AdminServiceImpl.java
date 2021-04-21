package cn.antchensw.netnote.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.antchensw.netnote.bean.Admin;
import cn.antchensw.netnote.bean.User;
import cn.antchensw.netnote.dao.AdminDao;
import cn.antchensw.netnote.service.AdminService;

/**
 * @author: Ant_Chen
 * @version：2020年6月24日 下午11:01:17
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public boolean getAdminByName(String name) throws SQLException {
        // TODO
        Admin admin = adminDao.getAdminByName(name);
        if (admin != null) {
            return true;
        }
        return false;
    }

    @Override
    public Admin getAdmin(String name, String password) throws SQLException {
        // TODO
        return adminDao.getAdmin(name, password);
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        // TODO
        return adminDao.getAllUsers();
    }

}
