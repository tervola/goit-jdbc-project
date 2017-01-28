package ua.com.tervola.jdbc.model.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.com.tervola.jdbc.model.Dish;
import ua.com.tervola.jdbc.model.DishDao;

import java.util.List;

/**
 * Created by user on 1/28/2017.
 */
public class JdbcDishDao  implements DishDao{

    private static Logger LOGGER = LogManager.getLogger(JdbcDishDao.class);

    private ComboPooledDataSource dataSource;

    public void setDataSource(ComboPooledDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addNewDish() {

    }

    @Override
    public void removeDish() {

    }

    @Override
    public Dish findDishByName() {
        return null;
    }

    @Override
    public List<Dish> findAllDishes() {
        return null;
    }
}
