package ua.com.tervola.jdbc.controller;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Data
public class TablesController {

    public static final List<String> MENU_LIST = Arrays.asList("tables", "Operations");

    @Autowired
    private final StoreController storeController;

    @Autowired
    private final  PreparedDishesController preparedDishesController;

    @Autowired
    private final  OrderController orderController;

    @Autowired
    private final  MenuController menuController;

    @Autowired
    private final  EmployeeController employeeController;

}
