package ua.com.tervola.jdbc.model;

import java.util.List;

/**
 * Created by user on 1/28/2017.
 */
public interface OrderDao {

    void createOrder(Order order);

    void closeOrder(int order_id);

    void updateOpenedOrder(int order_id);

    void removeOpenedOrder(int order_id);

    List<Order> findOpenedOrders();

    List<Order> findClosedOrders();
}
