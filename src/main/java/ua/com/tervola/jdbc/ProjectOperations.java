package ua.com.tervola.jdbc;

/**
 * Created by user on 3/1/2017.
 */
public enum ProjectOperations {
        UPDATE("update"),
        INSERT("insert"),
        DELETE("delete");

        final private String operation;

    ProjectOperations(String operation) {
            this.operation = operation;
        }

        @Override
        public String toString() {
            return this.operation;
        }
    }

