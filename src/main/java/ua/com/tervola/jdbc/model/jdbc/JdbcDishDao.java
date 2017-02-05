package ua.com.tervola.jdbc.model.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import ua.com.tervola.jdbc.controller.DatabaseController;
import ua.com.tervola.jdbc.model.Dish;
import ua.com.tervola.jdbc.model.DishDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 1/28/2017.
 */
public class JdbcDishDao extends AbstractJdbcTablesDao implements DishDao{

    private static Logger LOGGER = LogManager.getLogger(JdbcDishDao.class);
    private static String TABLE_DISH = "dish";
    private static String FIELD_CATEGORY = "category";
    private static String FIELD_COST = "cost";
    private static String FIELD_WEIGHT = "weight";
    private static String FIELD_DISH_ID = "dish_di";
    private static String FIELD_TITLE = "title";

    public JdbcDishDao(DatabaseController databaseController) {
        super(databaseController);
    }

    @Override
    @Transactional
    public void addNewDish(Dish dish) {
        try {
            StringBuilder sqlCommand = new StringBuilder();
            sqlCommand.append(String.format("INSERT INTO dish (%s,%s,%s,%s,%s )",FIELD_CATEGORY,FIELD_COST,FIELD_WEIGHT, FIELD_DISH_ID, FIELD_TITLE));
            sqlCommand.append("VALUES (?,?,?,?,?)");

            PreparedStatement preparedStatement = getDatabaseController().getConnection().prepareStatement(sqlCommand.toString());
            preparedStatement.setString(1,dish.getCategory());
            preparedStatement.setInt(2,dish.getCost());
            preparedStatement.setDouble(3,dish.getWeight());
            preparedStatement.setInt(4, dish.getDish_id());
            preparedStatement.setString(5,dish.getTitle());

            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e){
            LOGGER.error(String.format("Error, while updating %s table", TABLE_DISH));
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeDish(int id) {
        removeFromTable(id, TABLE_DISH, FIELD_DISH_ID);
    }

    @Override
    public Dish findDishByName(String name) {
        try {
            ResultSet resultSet = findInTable(name, TABLE_DISH, FIELD_TITLE);
            if (resultSet.next()){
                return createDish(resultSet);
            } else {
                throw new RuntimeException("Cannot findById dish with name " + name);
            }

        } catch (SQLException e){
            LOGGER.error("Error occured during connection to " + getDatabaseController().getManager().getJdbcUrl() +
                    " User is:" + getDatabaseController().getManager().getUser() +
                    " Password is: " + getDatabaseController().getManager().getPassword());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Dish> findAllDishes() {

        List<Dish> result = new ArrayList<>();
        try {
            ResultSet resultSet = findIntabledAllRecords(TABLE_DISH);
            while(resultSet.next()){
                Dish dish = createDish(resultSet);
                result.add(dish);
            }
        } catch (SQLException e) {
            LOGGER.error("Error occured during connection to " + getDatabaseController().getManager().getJdbcUrl());
            throw new RuntimeException(e);
        }
        return result;

    }

    @Override
    public Dish findDishById(int id) {
        try {
            ResultSet resultSet = findInTable(String.valueOf(id), TABLE_DISH, FIELD_DISH_ID );
            if (resultSet.next()){
                return createDish(resultSet);
            } else {
                throw new RuntimeException("Cannot findById dish with id " + id);
            }

        } catch (SQLException e){
            LOGGER.error("Error occured during connection to " + getDatabaseController().getManager().getJdbcUrl() +
                    " User is:" + getDatabaseController().getManager().getUser() +
                    " Password is: " + getDatabaseController().getManager().getPassword());
            throw new RuntimeException(e);
        }
    }

    private Dish createDish(ResultSet resultSet) throws SQLException {
        Dish dish = new Dish();
        dish.setCategory(resultSet.getString(FIELD_CATEGORY));
        dish.setCost(resultSet.getInt(FIELD_COST));
        dish.setDish_id(resultSet.getInt(FIELD_DISH_ID)); // TODO: rename field
        dish.setTitle(resultSet.getString(FIELD_TITLE));
        dish.setWeight(resultSet.getDouble(FIELD_WEIGHT));
        return dish;
    }

    @Override
    public List<Integer> getIndexes() throws SQLException {
        return getIndexesFromTable(FIELD_DISH_ID,TABLE_DISH);
    }
}
