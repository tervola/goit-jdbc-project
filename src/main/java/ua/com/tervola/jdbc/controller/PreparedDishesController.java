package ua.com.tervola.jdbc.controller;

import ua.com.tervola.jdbc.model.DatabaseDao;
import ua.com.tervola.jdbc.model.PreparedDishes;
import ua.com.tervola.jdbc.model.PreparedDishesDao;

import java.util.List;

/**
 * Created by user on 1/28/2017.
 */
public class PreparedDishesController {

    private PreparedDishesDao preparedDishesDao;

    public void setPreparedDishesDao(PreparedDishesDao preparedDishesDao) {
        this.preparedDishesDao = preparedDishesDao;
    }

    void addPreparedDish(String dish_name){
        this.preparedDishesDao.addPreparedDish(dish_name);
    }

    List<PreparedDishes> findAllDishes(){
        return this.preparedDishesDao.findAllDishes();
    }
}
