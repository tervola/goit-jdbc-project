package ua.com.tervola.jdbc.controller;

import ua.com.tervola.jdbc.model.DatabaseDao;
import ua.com.tervola.jdbc.model.PreparedDishesDao;

/**
 * Created by user on 1/28/2017.
 */
public class PreparedDishesController {

    private PreparedDishesDao preparedDishesDao;

    public void setPreparedDishesDao(PreparedDishesDao preparedDishesDao) {
        this.preparedDishesDao = preparedDishesDao;
    }
}
