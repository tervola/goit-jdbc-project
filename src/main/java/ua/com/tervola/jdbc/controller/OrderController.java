package ua.com.tervola.jdbc.controller;

import ua.com.tervola.jdbc.model.DatabaseDao;
import ua.com.tervola.jdbc.model.Order;
import ua.com.tervola.jdbc.model.OrderDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 1/28/2017.
 */
public class OrderController {

    private OrderDao orderDao;

    public OrderController(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void createOrder(Order order) {
        this.orderDao.createOrder(order);
    }

    public void closeOrder(int order_id) {
        this.orderDao.closeOrder(order_id);
    }

    public void updateOpenedOrder(String operation, int order_id, int dish_id) {
        this.orderDao.updateOpenedOrder(operation, order_id, dish_id);
    }

    public void removeOpenedOrder(int order_id) {
        this.orderDao.removeOpenedOrder(order_id);
    }

    public List<Order> findOpenedOrders() {
        return this.orderDao.findOrders(false);
    }

    public List<Order> findClosedOrders() {
        return this.orderDao.findOrders(true);
    }

    public List<String> findClosedOrdersAsString() {
        List<String> rval = new ArrayList<>();

        for (Order order : this.orderDao.findOrders(true)) {
            rval.add(order.toString());
        }
        return rval;

    }
}
