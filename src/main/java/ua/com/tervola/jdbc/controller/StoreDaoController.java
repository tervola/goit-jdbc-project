package ua.com.tervola.jdbc.controller;

import org.springframework.transaction.annotation.Transactional;
import ua.com.tervola.jdbc.model.DatabaseDao;
import ua.com.tervola.jdbc.model.Ingridient;
import ua.com.tervola.jdbc.model.StoreDao;

import java.util.List;

/**
 * Created by user on 1/28/2017.
 */
public class StoreDaoController {

    private StoreDao storeDao;
    public void setStoreDao(StoreDao storeDao) {
        this.storeDao = storeDao;
    }

    @Transactional
    void addNewIngridients(int ingridients_id, int amount){
        this.storeDao.addNewIngridients(ingridients_id,amount);
    }

    @Transactional
    void removeIngridients(int ingridients_id){
        this.storeDao.removeIngridients(ingridients_id);
    }

    @Transactional
    void updateIngridients(int ingridients_id, int amount){
        this.storeDao.updateIngridients(ingridients_id, amount);
    }

    @Transactional
    void findIngridientByName(String name){
        this.storeDao.findIngridientByName(name);
    }

    @Transactional
    List<Ingridient> findAllIngridients(){
        return this.storeDao.findAllIngridients();
    }

    @Transactional
    List<Ingridient> findAllIngridientsByAmount(int amount){
        return this.storeDao.findAllIngridients();
    }
}
