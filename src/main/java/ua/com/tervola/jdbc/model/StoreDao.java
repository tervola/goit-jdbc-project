package ua.com.tervola.jdbc.model;

import java.util.List;

/**
 * Created by user on 1/28/2017.
 */
public interface StoreDao {
    void addNewIngridients(Ingridient ingridient);

    void removeIngridients(int ingridients_id);

    boolean updateIngridients(Ingridient ingridient);

    Ingridient findIngridientByName(String Name);

    List<Ingridient> findAllIngridients();

    List<Ingridient> findAllIngridientsByAmount(int amount);
}
