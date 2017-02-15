package ua.com.tervola.jdbc;

/**
 * Created by user on 2/15/2017.
 */
public enum MainMenu {

    TABLES("List of tables"),
    EXIT("Exit");

    private String name;

    MainMenu(String s) {
        this.name = s;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
