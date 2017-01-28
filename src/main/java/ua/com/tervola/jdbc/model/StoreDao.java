package ua.com.tervola.jdbc.model;

import java.util.List;

/**
 * Created by user on 1/28/2017.
 */
public interface StoreDao {
    void addNewIngridients(int ingridients_id, int amount);

    void removeIngridients(int ingridients_id);

    void updateIngridients(int ingridients_id, int amount);

    void findIngridientByName(String Name);

    List<Ingridient> findAllIngridients();

    List<Ingridient> findAllIngridientsByAmount(int amount);
}
