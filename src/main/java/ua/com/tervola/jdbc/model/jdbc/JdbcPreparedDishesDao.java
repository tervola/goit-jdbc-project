package ua.com.tervola.jdbc.model.jdbc;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.com.tervola.jdbc.controller.DatabaseController;
import ua.com.tervola.jdbc.model.PreparedDishes;
import ua.com.tervola.jdbc.model.PreparedDishesDao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 1/28/2017.
 */
public class JdbcPreparedDishesDao extends AbstractJdbcTablesDao implements PreparedDishesDao{

    private static Logger LOGGER = LogManager.getLogger(JdbcPreparedDishesDao.class);

    private static String TABLE_PREPARED_DISHES = "prepared_dishes";
    private static String FIELD_DISH_ID = "dish_id";
    private static String FIELD_TITLE = "title";
    private static String FIELD_EMPLOYEE_ID = "employee_id";
    private static String FIELD_ORDER_ID = "order";
    private static String FIELD_DATE = "date";


    public JdbcPreparedDishesDao(DatabaseController databaseController) {
        super(databaseController);
    }

    @Override
    public void addPreparedDish(PreparedDishes preparedDishes) {
        try {
            StringBuilder sqlCommand = new StringBuilder();
            sqlCommand.append(String.format("INSERT INTO %s (%s,%s,%s,%s,%s)",
                    TABLE_PREPARED_DISHES,
                    FIELD_DISH_ID,
                    FIELD_TITLE,
                    FIELD_EMPLOYEE_ID,
                    FIELD_ORDER_ID,
                    FIELD_DATE));
            sqlCommand.append("VALUES (?,?,?,?,?,?,?)");

            PreparedStatement preparedStatement = getDatabaseController().getConnection().prepareStatement(sqlCommand.toString());
            preparedStatement.setInt(1, preparedDishes.getDishId());
            preparedStatement.setString(2, preparedDishes.getTitle());
            preparedStatement.setInt(3, preparedDishes.getEmployeeId());
            preparedStatement.setInt(4, preparedDishes.getOrderId());
            preparedStatement.setDate(5, Date.valueOf(preparedDishes.getDate()));

            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            LOGGER.error(String.format("Error, while updating %s table", TABLE_PREPARED_DISHES));
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PreparedDishes> findAllPreparedDishes() {

        List<PreparedDishes> result = new ArrayList<>();
        try {
            ResultSet resultSet = findIntabledAllRecords(TABLE_PREPARED_DISHES);
            while (resultSet.next()) {
                PreparedDishes preparedDishes = createPreparedDishes(resultSet);
                result.add(preparedDishes);
            }
        } catch (SQLException e) {
            LOGGER.error("Error occured during connection to " + getDatabaseController().getManager().getJdbcUrl());
            throw new RuntimeException(e);
        }
        return result;
    }

    private PreparedDishes createPreparedDishes(ResultSet resultSet) throws SQLException {

        PreparedDishes preparedDishes = new PreparedDishes();
        preparedDishes.setDishId(resultSet.getInt(FIELD_DISH_ID));
        preparedDishes.setTitle(resultSet.getString(FIELD_TITLE));
        preparedDishes.setEmployeeId(resultSet.getInt(FIELD_EMPLOYEE_ID));
        preparedDishes.setOrderId(resultSet.getInt(FIELD_ORDER_ID));
        preparedDishes.setDate(String.valueOf(resultSet.getDate(FIELD_DATE)));
        return preparedDishes;
    }

}
