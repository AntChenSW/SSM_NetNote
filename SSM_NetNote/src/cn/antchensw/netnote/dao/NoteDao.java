package cn.antchensw.netnote.dao;

import java.sql.SQLException;
import java.util.List;

import cn.antchensw.netnote.bean.Note;

/**
 * @author: Ant_Chen
 * @version：2020年6月6日 下午2:55:15
 */
public interface NoteDao {

    public Note getNote(int id) throws SQLException;

    public List<Note> getNotes(int user_id) throws SQLException;

    public int addNote(Note note) throws SQLException;

    public int delNote(int id) throws SQLException;

    public int updateNote(Note note) throws SQLException;
}
