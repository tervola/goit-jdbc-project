package ua.com.tervola.jdbc;

/**
 * Created by user on 2/14/2017.
 */
public enum ProjectTables {
    EMPLOYEE("employee"),
    DISH("dish"),
    MENU("menu"),
    ORDER("order"),
    PREPARED_DISHES("prepared_dishes"),
    STORAGE("storage"),
    SYSTEM_TABLE_INFORMATION_SCHEMA("information_schema.tables");

    final private String tableName;

    ProjectTables(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public String toString() {
        return this.tableName;
    }
}
