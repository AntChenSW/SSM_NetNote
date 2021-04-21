package cn.antchensw.netnote.bean;

/**
 * @author: Ant_Chen
 * @version：2020年6月6日 下午12:10:07
 */
public class Note {

    private int id = 0;
    private int level = 0;
    private String title = "无标题";
    private String date = "2020-01-01 12.12.12";
    private String content = "请输入内容";
    private int user_id = 0;

    public Note() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Note [id=" + id + ", level=" + level + ", title=" + title + ", date=" + date + ", content=" + content
                + ", user_id=" + user_id + "]";
    }

}
