package ua.com.tervola.jdbc.controller;

import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import ua.com.tervola.jdbc.model.DatabaseDao;
import ua.com.tervola.jdbc.model.Dish;
import ua.com.tervola.jdbc.model.DishDao;
import ua.com.tervola.jdbc.model.EmployeeDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 1/28/2017.
 */
@Component
public class DishController {

    private DishDao dishDao;

//    private PlatformTransactionManager txManager;
    public DishController( DishDao dishDao) {
//        this.txManager = txManager;
        this.dishDao = dishDao;
    }

    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }

    public void addNewDish(Dish dish) {
        this.dishDao.addNewDish(dish);
    }

    public void removeDish(int id) {
        this.dishDao.removeDish(id);
    }

    public Dish findDishByName(String title) {
        return this.dishDao.findDishByName(title);
    }

    public Dish findDishById(int id) {
        return this.dishDao.findDishById(id);
    }

    public List<Dish> findAllDishes() {
        return this.dishDao.findAllDishes();
    }

    public List<String> findAllDishesAsStringList(){

        List<String> rval = new ArrayList<>();
        for (Dish dish : this.dishDao.findAllDishes()) {
            rval.add(dish.toString());
        }
        return rval;
    }

}
