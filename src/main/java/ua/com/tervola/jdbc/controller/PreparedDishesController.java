package ua.com.tervola.jdbc.controller;

import ua.com.tervola.jdbc.model.PreparedDishes;
import ua.com.tervola.jdbc.model.PreparedDishesDao;
import ua.com.tervola.jdbc.model.jdbc.JdbcPreparedDishesDao;

import java.util.List;

/**
 * Created by user on 1/28/2017.
 */
public class PreparedDishesController {

    private PreparedDishesDao preparedDishesDao;

    public PreparedDishesController(PreparedDishesDao jdbcPreparedDishesDao) {
        this.preparedDishesDao = jdbcPreparedDishesDao;
    }

    public void addPreparedDish(PreparedDishes preparedDishes){
        this.preparedDishesDao.addPreparedDish(preparedDishes);
    }

    public List<PreparedDishes> findAllPreparedDishes(){
        return this.preparedDishesDao.findAllPreparedDishes();
    }
}
