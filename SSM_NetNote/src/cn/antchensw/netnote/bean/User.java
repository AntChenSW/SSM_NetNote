package cn.antchensw.netnote.bean;

/**
 * @author: Ant_Chen
 * @version：2020年6月6日 下午12:09:49
 */
public class User {

    private int id;
    private String name = "";
    private String password = "";
    private String email = "";
    private int level = 0;
    private int photo = 0;

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", level=" + level
                + "]";
    }

}
