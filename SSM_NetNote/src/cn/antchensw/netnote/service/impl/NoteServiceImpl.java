package cn.antchensw.netnote.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.antchensw.netnote.bean.Note;
import cn.antchensw.netnote.dao.NoteDao;
import cn.antchensw.netnote.service.NoteService;

/**
 * @author: Ant_Chen
 * @version：2020年6月24日 下午11:01:53
 */
@Service
@Transactional
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteDao noteDao;

    @Override
    public Note getNote(int id) throws SQLException {
        // TODO
        return noteDao.getNote(id);
    }

    @Override
    public List<Note> getNotes(int user_id) throws SQLException {
        // TODO
        return noteDao.getNotes(user_id);
    }

    @Override
    public boolean addNote(Note note) throws SQLException {
        // TODO
        int num = noteDao.addNote(note);
        if (num > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delNote(int id) throws SQLException {
        // TODO
        int num = noteDao.delNote(id);
        if (num > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateNote(Note note) throws SQLException {
        // TODO
        int num = noteDao.updateNote(note);
        if (num > 0) {
            return true;
        }
        return false;
    }

}
