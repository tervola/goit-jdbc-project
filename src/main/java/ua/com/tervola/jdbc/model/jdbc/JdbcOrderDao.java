package ua.com.tervola.jdbc.model.jdbc;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.com.tervola.jdbc.controller.DatabaseController;
import ua.com.tervola.jdbc.model.Order;
import ua.com.tervola.jdbc.model.OrderDao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 1/28/2017.
 */
public class JdbcOrderDao extends AbstractJdbcTablesDao implements OrderDao {

    private static Logger LOGGER = LogManager.getLogger(JdbcOrderDao.class);

    private static String TABLE_ORDER = "order";
    private static String TABLE_CONCATEENATE_ORDER_DISH = "concatenate_order_dish";
    private static String FIELD_ORDER_ID = "order_id";
    private static String FIELD_DISH_ID = "dish_id";
    private static String FIELD_EMPLOYEE = "employee";
    private static String FIELD_TABLE_NUMBER = "table_number";
    private static String FIELD_DATE = "date";
    private static String FIELD_CLOSED = "closed";
    private static String TRUE = "TRUE";
    private static String FALSE = "FALSE";

    private static String OPERATION_DELETE = "delete";
    private static String OPERATION_TRUNK = "trunk";
    private static String OPERATION_ADD = "add";

    public JdbcOrderDao(DatabaseController databaseController) {
        super(databaseController);
    }

    @Override
    public void createOrder(Order order) {
        try {
            StringBuilder sqlCommand = new StringBuilder();
            sqlCommand.append(String.format("INSERT INTO %s (%s,%s,%s,%s)", TABLE_ORDER, FIELD_ORDER_ID, FIELD_EMPLOYEE, FIELD_TABLE_NUMBER, FIELD_DATE));
            sqlCommand.append("VALUES (?,?,?,?)");

            PreparedStatement preparedStatement = getDatabaseController().getConnection().prepareStatement(sqlCommand.toString());
            preparedStatement.setInt(1, order.getOrder_id());
            preparedStatement.setInt(2, order.getEmployee_id());
            preparedStatement.setInt(3, order.getTebleNumber());
            preparedStatement.setDate(4, Date.valueOf(order.getDate()));

            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            LOGGER.error(String.format("Error, while updating %s table", TABLE_ORDER));
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private Order createOrder(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setOrder_id(resultSet.getInt(FIELD_ORDER_ID));
        order.setEmployee_id(resultSet.getInt(FIELD_EMPLOYEE));
        order.setDate(resultSet.getString(FIELD_DATE));
        order.setTebleNumber(resultSet.getInt(FIELD_TABLE_NUMBER));
        return order;
    }

    @Override
    public boolean closeOrder(int order_id) {
        String dataSet = String.format("%s = %s", FIELD_CLOSED, TRUE);
        return updateTable(order_id, TABLE_ORDER, dataSet, FIELD_CLOSED);
    }

    @Override
    public boolean updateOpenedOrder(String operation, int order_id, int dish_id) {
        boolean success = false;
        StringBuilder sqlCommand = new StringBuilder();
        try {
            if (OPERATION_ADD.equals(operation)) {
                sqlCommand.append(String.format("INSERT INTO %s (%s,%s)", TABLE_CONCATEENATE_ORDER_DISH, FIELD_ORDER_ID, FIELD_DISH_ID));
                sqlCommand.append("VALUES (?,?)");
                PreparedStatement preparedStatement = getDatabaseController().getConnection().prepareStatement(sqlCommand.toString());
                preparedStatement.setInt(1, order_id);
                preparedStatement.setInt(2, dish_id);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                success = true;
            } else if (OPERATION_DELETE.equals(operation)) {
                sqlCommand.append(String.format("DELETE FROM %s WHERE %s = %s AND %s = %s", TABLE_CONCATEENATE_ORDER_DISH, FIELD_DISH_ID, dish_id, FIELD_ORDER_ID, order_id));
                PreparedStatement preparedStatement = getDatabaseController().getConnection().prepareStatement(sqlCommand.toString());
                preparedStatement.executeUpdate();
                preparedStatement.close();
                success = true;
            } else if (OPERATION_TRUNK.equals(operation)) {
                sqlCommand.append(String.format("DELETE FROM %s", TABLE_CONCATEENATE_ORDER_DISH));
                PreparedStatement preparedStatement = getDatabaseController().getConnection().prepareStatement(sqlCommand.toString());
                preparedStatement.executeUpdate();
                preparedStatement.close();
                success = true;
            }

        } catch (SQLException e) {
            LOGGER.error(String.format("Error, while updating %s table", TABLE_CONCATEENATE_ORDER_DISH));
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return success;
    }

    @Override
    public boolean removeOpenedOrder(int order_id) {
        boolean success = false;
        StringBuilder sqlCommand = new StringBuilder();
        try{
            sqlCommand.append(String.format("DELETE FROM %s WHERE %s = %s)", TABLE_CONCATEENATE_ORDER_DISH, FIELD_ORDER_ID, order_id));
            PreparedStatement preparedStatement = getDatabaseController().getConnection().prepareStatement(sqlCommand.toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            success = true;
        } catch (SQLException e) {
        LOGGER.error(String.format("Error, while removing from %s ", TABLE_CONCATEENATE_ORDER_DISH));
        System.out.println(e.getMessage());
        throw new RuntimeException(e);
    }
        return success;
    }

    @Override
    public List<Order> findOrders(boolean closed) {
        List<Order> result = new ArrayList<>();

        try {
            ResultSet resultSet = findInTable(FIELD_CLOSED, TABLE_ORDER, String.valueOf(closed), CONDITION_EQ);
            while (resultSet.next()) {
                Order order = createOrder(resultSet);
                result.add(order);
            }
        } catch (SQLException e) {
            LOGGER.error("Error occured during connection to " + getDatabaseController().getManager().getJdbcUrl());
            throw new RuntimeException(e);
        }

        return result;
    }

}
