package ua.com.tervola.jdbc.model.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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

    private ComboPooledDataSource dataSource;

    public void setDataSource(ComboPooledDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addNewDish(Dish dish) {
        try {
            Connection connection = dataSource.getConnection();
            StringBuilder sqlCommand = new StringBuilder();
            sqlCommand.append("INSERT INTO dish (category,cost,weight,dish_di,title )");
            sqlCommand.append("VALUES (?,?,?,?,?)");

            PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand.toString());
            preparedStatement.setString(1,dish.getCategory());
            preparedStatement.setInt(2,dish.getCost());
            preparedStatement.setDouble(3,dish.getWeight());
            preparedStatement.setInt(4, dish.getDish_id());
            preparedStatement.setString(5,dish.getTitle());

            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e){
            LOGGER.error("Error, while updating EMPLOYEE table");
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeDish(int id) {
        removeFromTable(id, "dish");
    }

    @Override
    public Dish findDishByName(String title) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM dish WHERE title = ?");
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return createDish(resultSet);
            } else {
                throw new RuntimeException("Cannot find dish with id " + title);
            }

        } catch (SQLException e){
            LOGGER.error("Error occured during connection to " + dataSource.getJdbcUrl() + " User is:" + dataSource.getUser() + " Password is: " + dataSource.getPassword());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Dish> findAllDishes() {

        List<Dish> result = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM dish");
            while(resultSet.next()){
                Dish dish = createDish(resultSet);
                result.add(dish);
            }
        } catch (SQLException e) {
            LOGGER.error("Error occured during connection to " + dataSource.getJdbcUrl());
            throw new RuntimeException(e);
        }
        return result;

    }

    private Dish createDish(ResultSet resultSet) throws SQLException {
        Dish dish = new Dish();
        dish.setCategory(resultSet.getString("category"));
        dish.setCost(resultSet.getInt("cost"));
        dish.setDish_id(resultSet.getInt("dish_di")); // TODO: rename field
        dish.setTitle(resultSet.getString("title"));
        dish.setWeight(resultSet.getDouble("weight"));
        return dish;
    }
}
