package cn.antchensw.netnote.service;

import java.sql.SQLException;
import java.util.List;

import cn.antchensw.netnote.bean.Note;

/**
 * @author: Ant_Chen
 * @version：2020年6月24日 下午10:56:58
 */
public interface NoteService {

    public Note getNote(int id) throws SQLException;

    public List<Note> getNotes(int user_id) throws SQLException;

    public boolean addNote(Note note) throws SQLException;

    public boolean delNote(int id) throws SQLException;

    public boolean updateNote(Note note) throws SQLException;
}
