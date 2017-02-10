package ua.com.tervola.jdbc.model;

/**
 * Created by user on 1/28/2017.
 */
public class Order {

    int order_id;
    int employee_id;
    int tebleNumber;
    String date;
    boolean isOpened;

    public Order() {
        this.isOpened = true;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public int getTebleNumber() {
        return tebleNumber;
    }

    public void setTebleNumber(int tebleNumber) {
        this.tebleNumber = tebleNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", employee_id=" + employee_id +
                ", tebleNumber=" + tebleNumber +
                ", date='" + date + '\'' +
                ", isOpened=" + isOpened +
                '}';
    }
}
