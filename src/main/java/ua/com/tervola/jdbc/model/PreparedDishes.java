package ua.com.tervola.jdbc.model;

/**
 * Created by user on 1/28/2017.
 */
public class PreparedDishes {

    int dishId;
    String title;
    int employeeId;
    int orderId;
    String date;

    public int getDishId() {
        return dishId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PreparedDishes{" +
                "dishId=" + dishId +
                ", title='" + title + '\'' +
                ", employeeId=" + employeeId +
                ", orderId='" + orderId + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
