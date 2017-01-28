package ua.com.tervola.jdbc.model;

import java.util.List;

/**
 * Created by user on 1/28/2017.
 */
public interface PreparedDishesDao {
    void addPreparedDish(String dish_name);

    List<PreparedDishes> findAllDishes();
}
