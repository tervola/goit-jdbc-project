package ua.com.tervola.jdbc.model;

import java.util.List;

/**
 * Created by user on 1/28/2017.
 */
public interface MenuDao {
    void createNewMenu(ProjectMenu menu);
    void removeMenu(int id);
    boolean modifyMenu(int id, ProjectMenu projectMenu);
    ProjectMenu findMenuByName(String menuName);
    ProjectMenu findMenuById(int id);
    List<ProjectMenu> findAllMenu();
}
