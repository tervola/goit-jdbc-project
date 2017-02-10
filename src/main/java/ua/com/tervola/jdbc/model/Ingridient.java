package ua.com.tervola.jdbc.model;

/**
 * Created by user on 1/28/2017.
 */
public class Ingridient {

    int ingridient_id;
    int amount;

    public int getIngridient_id() {
        return ingridient_id;
    }

    public void setIngridient_id(int ingridient_id) {
        this.ingridient_id = ingridient_id;
    }

    public int getIngridientAmount() {
        return amount;
    }

    public void setIngridientAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Ingridient{" +
                "ingridient_id=" + ingridient_id +
                ", amount=" + amount +
                '}';
    }
}
