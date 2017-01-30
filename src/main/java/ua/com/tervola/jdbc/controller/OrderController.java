package ua.com.tervola.jdbc.controller;

import ua.com.tervola.jdbc.model.DatabaseDao;
import ua.com.tervola.jdbc.model.Order;
import ua.com.tervola.jdbc.model.OrderDao;

import java.util.List;

/**
 * Created by user on 1/28/2017.
 */
public class OrderController {

    private OrderDao orderDao;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    void createOrder(Order order){
        this.orderDao.createOrder(order);
    }

    void closeOrder(int order_id) {
        this.orderDao.closeOrder(order_id);
    }

    void updateOpenedOrder(int order_id){
        this.orderDao.updateOpenedOrder(order_id);
    }

    void removeOpenedOrder(int order_id){
        this.orderDao.removeOpenedOrder(order_id);
    }

    List<Order> findOpenedOrders(){
        return this.orderDao.findOpenedOrders();
    }

    List<Order> findClosedOrders() {
        return this.orderDao.findClosedOrders();
    }
}
