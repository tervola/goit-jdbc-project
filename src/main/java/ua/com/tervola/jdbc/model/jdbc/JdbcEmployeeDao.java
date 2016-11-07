package ua.com.tervola.jdbc.model.jdbc;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.sql.DataSource;

/**
 * Created by user on 11/4/2016.
 */
public class JdbcEmployeeDao {
    private DataSource dataSource;
    private Logger LOGGER = LogManager.getLogger(JdbcEmployeeDao.class);

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void sample(){
        LOGGER.info("into sample");
    }
}
