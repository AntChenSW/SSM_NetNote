package cn.antchensw.netnote.service.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.antchensw.netnote.bean.User;
import cn.antchensw.netnote.dao.UserDao;
import cn.antchensw.netnote.service.UserService;

/**
 * @author: Ant_Chen
 * @version：2020年6月24日 下午11:01:35
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean getUserByName(String name) throws SQLException {
        // TODO
        User user = userDao.getUserByName(name);

        if (user != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean getUserByEmail(String email) throws SQLException {
        // TODO
        User user = userDao.getUserByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }

    @Override
    public User getUser(String name, String password) throws SQLException {
        // TODO
        return userDao.getUser(name, password);
    }

    @Override
    public boolean addUser(User user) throws SQLException {
        // TODO
        int num = userDao.addUser(user);
        if (num > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delUser(int id) throws SQLException {
        // TODO
        int num = userDao.delUser(id);
        if (num > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        // TODO
        int num = userDao.updateUser(user);
        if (num > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String getPass(String email) throws SQLException {
        // TODO
        return userDao.getPass(email);
    }

}
