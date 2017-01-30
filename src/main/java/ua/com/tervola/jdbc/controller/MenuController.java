package ua.com.tervola.jdbc.controller;

import ua.com.tervola.jdbc.model.DatabaseDao;
import ua.com.tervola.jdbc.model.MenuDao;
import ua.com.tervola.jdbc.model.ProjectMenu;

import java.util.List;

/**
 * Created by user on 1/28/2017.
 */
public class MenuController {

    private MenuDao menuDao;
    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    void createNewMenu(){
        this.menuDao.createNewMenu();
    }
    void removeMenu() {
        this.menuDao.removeMenu();
    }
    void modifyMenu(int menu_id) {
        this.menuDao.modifyMenu(menu_id);
    }
    ProjectMenu fingMenuByName(String menuName) {
        return this.menuDao.fingMenuByName(menuName);
    }
    List<ProjectMenu> findAllMenu() {
        return this.menuDao.findAllMenu();
    }
}
