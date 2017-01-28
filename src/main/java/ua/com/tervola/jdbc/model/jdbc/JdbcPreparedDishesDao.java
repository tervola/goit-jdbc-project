package ua.com.tervola.jdbc.model.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.com.tervola.jdbc.model.PreparedDishes;
import ua.com.tervola.jdbc.model.PreparedDishesDao;

import java.util.List;

/**
 * Created by user on 1/28/2017.
 */
public class JdbcPreparedDishesDao implements PreparedDishesDao{

    private static Logger LOGGER = LogManager.getLogger(JdbcPreparedDishesDao.class);

    private ComboPooledDataSource dataSource;

    public void setDataSource(ComboPooledDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addPreparedDish(String dish_name) {

    }

    @Override
    public List<PreparedDishes> findAllDishes() {
        return null;
    }

}
