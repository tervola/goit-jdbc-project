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

    void addNewDish(Dish dish) {
        this.dishDao.addNewDish(dish);
    }

    void removeDish(int id) {
        this.dishDao.removeDish(id);
    }

    Dish findDishByName(String title) {
        return this.dishDao.findDishByName(title);
    }

    Dish findDishById(int id) {
        return this.dishDao.findDishById(id);
    }

    List<Dish> findAllDishes() {
        return this.dishDao.findAllDishes();
    }

}
