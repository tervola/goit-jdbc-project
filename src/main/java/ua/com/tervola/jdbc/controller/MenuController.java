package ua.com.tervola.jdbc.controller;

import ua.com.tervola.jdbc.model.DatabaseDao;
import ua.com.tervola.jdbc.model.MenuDao;

/**
 * Created by user on 1/28/2017.
 */
public class MenuController {

    private MenuDao menuDao;
    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }
}
