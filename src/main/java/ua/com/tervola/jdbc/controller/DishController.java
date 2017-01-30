package ua.com.tervola.jdbc.controller;

import ua.com.tervola.jdbc.model.DatabaseDao;
import ua.com.tervola.jdbc.model.Dish;
import ua.com.tervola.jdbc.model.DishDao;

import java.util.List;

/**
 * Created by user on 1/28/2017.
 */
public class DishController {
    private DishDao dishDao;

    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }

    void addNewDish() {
        this.dishDao.addNewDish();
    }

    void removeDish() {
        this.dishDao.removeDish();
    }

    Dish findDishByName() {
        return this.dishDao.findDishByName();
    }

    List<Dish> findAllDishes() {
        return this.dishDao.findAllDishes();
    }

}
