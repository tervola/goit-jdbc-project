package ua.com.tervola.jdbc.model.jdbc;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.com.tervola.jdbc.controller.DatabaseController;
import ua.com.tervola.jdbc.model.Ingridient;
import ua.com.tervola.jdbc.model.ProjectTables;
import ua.com.tervola.jdbc.model.StoreDao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 1/28/2017.
 */
public class JdbcStoreDao extends AbstractJdbcTablesDao implements StoreDao {

    private static Logger LOGGER = LogManager.getLogger(JdbcStoreDao.class);
    private static String FIELD_ID = "idgridient_id";
    private static String FIELD_AMOUNT = "amount";

    public JdbcStoreDao(DatabaseController databaseController) {
        super(databaseController);
    }

    @Override
    public void addNewIngridients(Ingridient ingridient) {
        try {
            StringBuilder sqlCommand = new StringBuilder();
            sqlCommand.append(String.format("INSERT INTO %s (%s,%s,%s,%s,%s)",
                    ProjectTables.STORAGE,
                    FIELD_ID,
                    FIELD_AMOUNT));
            sqlCommand.append("VALUES (?,?,?)");

            PreparedStatement preparedStatement = getDatabaseController().getConnection().prepareStatement(sqlCommand.toString());
            preparedStatement.setInt(1, ingridient.getIngridient_id());
            preparedStatement.setInt(2, ingridient.getIngridientAmount());

            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            LOGGER.error(String.format("Error, while updating %s table", ProjectTables.STORAGE));
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeIngridients(int ingridients_id) {
        removeFromTable(ingridients_id, ProjectTables.STORAGE, FIELD_ID);
    }

    @Override
    public boolean updateIngridients(Ingridient ingridient) {
        String dataSet = String.format("%s = %s", FIELD_AMOUNT, ingridient.getIngridientAmount());
        return updateTable(ingridient.getIngridient_id(), ProjectTables.STORAGE, dataSet, FIELD_ID);
    }

    @Override
    public Ingridient findIngridientByName(String name) {
        try {
            //"SELECT * FROM public.%s WHERE %s = %s
            ResultSet resultSet = findInTable(name, ProjectTables.STORAGE, FIELD_ID, CONDITION_EQ);
            if (resultSet.next()) {
                return createIngridient(resultSet);
            } else {
                throw new RuntimeException("Cannot findById inggridient with name: " + name);
            }

        } catch (SQLException e) {
//            LOGGER.error("Error occured during connection to " + dataSource.getJdbcUrl() + " User is:" + dataSource.getUser() + " Password is: " + dataSource.getPassword());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Ingridient> findAllIngridients() {

        List<Ingridient> result = new ArrayList<>();
        try {
            ResultSet resultSet = findIntabledAllRecords(ProjectTables.STORAGE);
            while (resultSet.next()) {
                Ingridient ingridient = createIngridient(resultSet);
                result.add(ingridient);
            }
        } catch (SQLException e) {
//            LOGGER.error("Error occured during connection to " + dataSource.getJdbcUrl());
            throw new RuntimeException(e);
        }
        return result;

    }

    private Ingridient createIngridient(ResultSet resultSet) throws SQLException {
        Ingridient ingridient = new Ingridient();
        ingridient.setIngridient_id(resultSet.getInt(FIELD_ID));
        ingridient.setIngridientAmount(resultSet.getInt(FIELD_AMOUNT));
        return ingridient;
    }

    @Override
    public List<Ingridient> findAllIngridientsByAmount(int amount) {
        List<Ingridient> result = new ArrayList<>();
        try {
            ResultSet resultSet = findInTable(FIELD_AMOUNT, ProjectTables.STORAGE, String.valueOf(amount), CONDITION_GT);
            if (resultSet.next()) {
                result.add(createIngridient(resultSet));
            } else {
                throw new RuntimeException("Cannot findById ingridients with amount: " + amount);
            }

        } catch (SQLException e) {
//            LOGGER.error("Error occured during connection to " + dataSource.getJdbcUrl() + " User is:" + dataSource.getUser() + " Password is: " + dataSource.getPassword());
            throw new RuntimeException(e);
        }
        return result;
    }

}
