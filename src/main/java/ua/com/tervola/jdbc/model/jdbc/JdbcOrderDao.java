package ua.com.tervola.jdbc.model.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.com.tervola.jdbc.model.Order;
import ua.com.tervola.jdbc.model.OrderDao;

import java.util.List;

/**
 * Created by user on 1/28/2017.
 */
public class JdbcOrderDao implements OrderDao {

    private static Logger LOGGER = LogManager.getLogger(JdbcOrderDao.class);

    private ComboPooledDataSource dataSource;

    public void setDataSource(ComboPooledDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void createOrder(Order order) {

    }

    @Override
    public void closeOrder(int order_id) {

    }

    @Override
    public void updateOpenedOrder(int order_id) {

    }

    @Override
    public void removeOpenedOrder(int order_id) {

    }

    @Override
    public List<Order> findOpenedOrders() {
        return null;
    }

    @Override
    public List<Order> findClosedOrders() {
        return null;
    }
}
