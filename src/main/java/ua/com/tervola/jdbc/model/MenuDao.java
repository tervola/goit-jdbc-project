package ua.com.tervola.jdbc.model;

import java.util.List;

/**
 * Created by user on 1/28/2017.
 */
public interface MenuDao {
    void createNewMenu();
    void removeMenu();
    void modifyMenu(int menu_id);
    ProjectMenu fingMenuByName(String menuName);
    List<ProjectMenu> findAllMenu();
}
