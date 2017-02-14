package ua.com.tervola.jdbc.model.jdbc;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.com.tervola.jdbc.controller.DatabaseController;
import ua.com.tervola.jdbc.model.MenuDao;
import ua.com.tervola.jdbc.model.ProjectMenu;
import ua.com.tervola.jdbc.model.ProjectTables;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 1/28/2017.
 */
public class JdbcMenuDao extends AbstractJdbcTablesDao implements MenuDao {
    private static Logger LOGGER = LogManager.getLogger(JdbcMenuDao.class);

    private static String FIELD_ID = "menu_id";
    private static String FIELD_NAME = "menu_name";

    public JdbcMenuDao(DatabaseController databaseController) {
        super(databaseController);
    }

    @Override
    public void createNewMenu(ProjectMenu menu) {
        try {
            StringBuilder sqlCommand = new StringBuilder();
            sqlCommand.append(String.format("INSERT INTO %s (%s,%s )", ProjectTables.MENU, FIELD_ID, FIELD_NAME));
            sqlCommand.append("VALUES (?,?)");

            PreparedStatement preparedStatement = getDatabaseController().getConnection().prepareStatement(sqlCommand.toString());
            preparedStatement.setInt(1, menu.getMenu_id());
            preparedStatement.setString(2, menu.getMenuName());

            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            LOGGER.error(String.format("Error, while updating %s table", ProjectTables.MENU));
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private ProjectMenu createProjectMenu(ResultSet resultSet) throws SQLException {
        ProjectMenu projectMenu = new ProjectMenu();
        projectMenu.setMenu_id(resultSet.getInt(FIELD_ID));
        projectMenu.setMenuName(resultSet.getString(FIELD_NAME));
        return projectMenu;
    }

    @Override
    public void removeMenu(int id) {
        removeFromTable(id, ProjectTables.MENU, FIELD_ID);
    }

    @Override
    public boolean modifyMenu(ProjectMenu projectMenu) {
        String dataSet = String.format("%s = %s", FIELD_NAME, projectMenu.getMenuName());
        return updateTable(projectMenu.getMenu_id(), ProjectTables.MENU, dataSet, FIELD_ID);
    }

    @Override
    public ProjectMenu findMenuByName(String menuName) {
        try {
            ResultSet resultSet = findInTable(menuName, ProjectTables.MENU, FIELD_NAME, CONDITION_EQ);
            if (resultSet.next()) {
                return createProjectMenu(resultSet);
            } else {
                throw new RuntimeException("Cannot findById dish with name " + menuName);
            }

        } catch (SQLException e) {
            LOGGER.error("Error occured during connection to " + getDatabaseController().getManager().getJdbcUrl() +
                    " User is:" + getDatabaseController().getManager().getUser() +
                    " Password is: " + getDatabaseController().getManager().getPassword());
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProjectMenu findMenuById(int id) {
        try {
            ResultSet resultSet = findInTable(String.valueOf(id), ProjectTables.MENU, FIELD_ID, CONDITION_EQ);
            if (resultSet.next()) {
                return createProjectMenu(resultSet);
            } else {
                throw new RuntimeException("Cannot findById dish with id " + id);
            }

        } catch (SQLException e) {
            LOGGER.error("Error occured during connection to " + getDatabaseController().getManager().getJdbcUrl() +
                    " User is:" + getDatabaseController().getManager().getUser() +
                    " Password is: " + getDatabaseController().getManager().getPassword());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ProjectMenu> findAllMenu() {
        List<ProjectMenu> result = new ArrayList<>();
        try {
            ResultSet resultSet = findIntabledAllRecords(ProjectTables.MENU);
            while (resultSet.next()) {
                ProjectMenu projectMenu = createProjectMenu(resultSet);
                result.add(projectMenu);
            }
        } catch (SQLException e) {
            LOGGER.error("Error occured during connection to " + getDatabaseController().getManager().getJdbcUrl());
            throw new RuntimeException(e);
        }
        return result;
    }
}
