package ua.com.tervola.jdbc.model.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.com.tervola.jdbc.model.Ingridient;
import ua.com.tervola.jdbc.model.StoreDao;

import java.util.List;

/**
 * Created by user on 1/28/2017.
 */
public class JdbcStoreDao implements StoreDao {

    private static Logger LOGGER = LogManager.getLogger(JdbcStoreDao.class);

    private ComboPooledDataSource dataSource;

    public void setDataSource(ComboPooledDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addNewIngridients(int ingridients_id, int amount) {

    }

    @Override
    public void removeIngridients(int ingridients_id) {

    }

    @Override
    public void updateIngridients(int ingridients_id, int amount) {

    }

    @Override
    public void findIngridientByName(String Name) {

    }

    @Override
    public List<Ingridient> findAllIngridients() {
        return null;
    }

    @Override
    public List<Ingridient> findAllIngridientsByAmount(int amount) {
        return null;
    }
}
