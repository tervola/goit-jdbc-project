package ua.com.tervola.jdbc.model.jdbc;

import ua.com.tervola.jdbc.model.Ingridient;
import ua.com.tervola.jdbc.model.StoreDao;

/**
 * Created by user on 1/28/2017.
 */
public class JdbcStoreDao implements StoreDao {
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
    public Ingridient findAllIngridients() {
        return null;
    }

    @Override
    public Ingridient findAllIngridientsByAmount(int amount) {
        return null;
    }
}
