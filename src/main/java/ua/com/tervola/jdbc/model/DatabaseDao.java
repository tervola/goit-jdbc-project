package ua.com.tervola.jdbc.model;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by user on 11/18/2016.
 */
public interface DatabaseDao {

    boolean isConnected() throws SQLException;

    List<String> getAllTables() throws SQLException;
}
