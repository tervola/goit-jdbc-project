package ua.com.tervola.jdbc.controller;

import ua.com.tervola.jdbc.model.DatabaseDao;
import ua.com.tervola.jdbc.model.StoreDao;

/**
 * Created by user on 1/28/2017.
 */
public class StoreDaoController {

    private StoreDao storeDao;
    public void setStoreDao(StoreDao storeDao) {
        this.storeDao = storeDao;
    }
}
