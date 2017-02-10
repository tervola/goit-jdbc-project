package ua.com.tervola.jdbc.model;

import java.util.List;

/**
 * Created by user on 1/28/2017.
 */
public interface OrderDao {

    void createOrder(Order order);

    boolean closeOrder(int order_id);

    boolean updateOpenedOrder(String operation, int order_id, int dish_id);

    boolean removeOpenedOrder(int order_id);

    List<Order> findOrders(boolean closed);

}
