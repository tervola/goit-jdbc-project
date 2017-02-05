package ua.com.tervola.jdbc.controller;

import ua.com.tervola.jdbc.model.MenuDao;
import ua.com.tervola.jdbc.model.ProjectMenu;

import java.util.List;

/**
 * Created by user on 1/28/2017.
 */
public class MenuController {

    private MenuDao menuDao;

    public MenuController(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    public void createNewMenu(ProjectMenu menu){
        this.menuDao.createNewMenu(menu);
    }
    public void removeMenu(int id) {
        this.menuDao.removeMenu(id);
    }
    public boolean modifyMenu(int menu_id, ProjectMenu menu) {
        return this.menuDao.modifyMenu(menu_id, menu);
    }
    public ProjectMenu findMenuByName(String menuName) {
        return this.menuDao.findMenuByName(menuName);
    }
    public ProjectMenu findMenuById(int id) {
        return this.menuDao.findMenuById(id);
    }
    public List<ProjectMenu> findAllMenu() {
        return this.menuDao.findAllMenu();
    }
}
