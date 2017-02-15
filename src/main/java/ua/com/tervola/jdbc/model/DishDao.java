package ua.com.tervola.jdbc.model;

import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by user on 1/28/2017.
 */
public interface DishDao {

    void addNewDish(Dish dish);

    void removeDish(int id);

    Dish findDishByName(String title);

    List<Dish> findAllDishes();

    Dish findDishById(int id);

    List<Integer> getIndexes() throws SQLException;
}
