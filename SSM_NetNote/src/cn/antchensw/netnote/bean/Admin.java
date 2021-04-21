package cn.antchensw.netnote.bean;

/**
 * @author: Ant_Chen
 * @version：2020年6月6日 下午12:09:57
 */
public class Admin {

    private int id;
    private String name;
    private String password;

    public Admin() {
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

    @Override
    public String toString() {
        return "Admin [id=" + id + ", name=" + name + ", password=" + password + "]";
    }

}
