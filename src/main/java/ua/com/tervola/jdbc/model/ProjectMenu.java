package ua.com.tervola.jdbc.model;

/**
 * Created by user on 1/28/2017.
 */
public class ProjectMenu {

    int menu_id;
    String menuName;

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    @Override
    public String toString() {
        return "ProjectMenu{" +
                "menu_id=" + menu_id +
                ", menuName='" + menuName + '\'' +
                '}';
    }
}
