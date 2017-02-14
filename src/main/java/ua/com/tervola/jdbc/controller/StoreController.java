package ua.com.tervola.jdbc.controller;

import org.springframework.transaction.annotation.Transactional;
import ua.com.tervola.jdbc.model.DatabaseDao;
import ua.com.tervola.jdbc.model.Ingridient;
import ua.com.tervola.jdbc.model.StoreDao;
import ua.com.tervola.jdbc.model.jdbc.JdbcStoreDao;

import java.util.List;

/**
 * Created by user on 1/28/2017.
 */
public class StoreController {

    private StoreDao storeDao;

    public StoreController(StoreDao jdbcStoreDao) {
        this.storeDao = jdbcStoreDao;
    }

    public void addNewIngridients(Ingridient ingridient){
        this.storeDao.addNewIngridients(ingridient);
    }

    public void removeIngridients(int ingridients_id){
        this.storeDao.removeIngridients(ingridients_id);
    }

    public void updateIngridients(Ingridient ingridient){
        this.storeDao.updateIngridients(ingridient);
    }

    public void findIngridientByName(String name){
        this.storeDao.findIngridientByName(name);
    }

    public List<Ingridient> findAllIngridients(){
        return this.storeDao.findAllIngridients();
    }

    public List<Ingridient> findAllIngridientsByAmount(int amount){
        return this.storeDao.findAllIngridients();
    }
}
