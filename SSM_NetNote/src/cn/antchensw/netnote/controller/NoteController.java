package cn.antchensw.netnote.controller;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.antchensw.netnote.bean.Note;
import cn.antchensw.netnote.bean.User;
import cn.antchensw.netnote.service.NoteService;

/**
 * @author: Ant_Chen
 * @version：2020年6月25日 下午8:47:08
 */
@Controller
public class NoteController {

    @Autowired
    private NoteService noteService;

    @RequestMapping("/toedit")
    public String toEdit(String note_id, HttpSession session) throws NumberFormatException, SQLException {
        Note note = noteService.getNote(Integer.parseInt(note_id));
        session.setAttribute("note_session", note);
        return "note";
    }

    @RequestMapping(value = "/note.add", method = RequestMethod.POST)
    public String addNote(String title, String level, String content, HttpSession session)
            throws UnsupportedEncodingException {
        int user_id = ((User) session.getAttribute("user_session")).getId();
        if (!title.equals("")) {
            Calendar curTime = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String addDate = sdf.format(curTime.getTime());
            System.out.println(content);
            Note note = new Note();
            note.setTitle(title);
            note.setDate(addDate);
            note.setLevel(Integer.parseInt(level));
            note.setContent(content);
            note.setUser_id(user_id);
            try {
                if (noteService.addNote(note)) {
                    session.setAttribute("msg_login", "笔记添加成功!");
                } else {
                    session.setAttribute("msg_login", "笔记添加失败!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return "redirect:index";
        } else {
            session.setAttribute("msg_newnote", "笔记标题不能为空!");
            return "redirect:note";
        }
    }

    @RequestMapping(value = "/note.update", method = RequestMethod.POST)
    public String updateNote(String id, String title, String level, String content, HttpSession session) {
        int user_id = ((User) session.getAttribute("user_session")).getId();
        Calendar curTime = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String updateDate = sdf.format(curTime.getTime());
        Note note = new Note();
        note.setId(Integer.parseInt(id));
        note.setTitle(title);
        note.setDate(updateDate);
        note.setLevel(Integer.parseInt(level));
        note.setContent(content);
        note.setUser_id(user_id);
        try {
            if (noteService.updateNote(note)) {
                session.setAttribute("msg_login", "笔记" + id + "更新成功!");
            } else {
                session.setAttribute("msg_login", "笔记" + id + "更新失败!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "redirect:index";
    }

    @RequestMapping("/note.delete")
    public String deleteNote(String note_id, HttpSession session) {
        try {
            if (noteService.delNote(Integer.parseInt(note_id))) {

                session.setAttribute("msg_login", "笔记" + note_id + "已删除!");
            } else {
                session.setAttribute("msg_login", "笔记删除失败!");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "redirect:index";
    }

}
