package org.academiadecodigo.javabank.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final String DEFAULT_USER = "root";
    private static final String DEFAULT_PASS = "";
    private static final String DEFAULT_HOST = "localhost";
    private static final String DEFAULT_DB = "javabank";

    private static final String CONNECTOR = "jdbc:mysql:";

    private String dbUrl;
    private String user;
    private String pass;
    private Connection connection;

    public ConnectionManager(String user, String pass, String host, String database) {
        this.user = user;
        this.pass = pass;
        this.dbUrl = CONNECTOR + "//" + host + "/" + database;
    }

    public ConnectionManager() {
        this(DEFAULT_USER, DEFAULT_PASS, DEFAULT_HOST, DEFAULT_DB);
    }

    public Connection getConnection() {

        try {
            if (connection == null) {
                connection = DriverManager.getConnection(dbUrl, user, pass);
            }
        } catch (SQLException ex) {
            System.out.println("Failure to connect to database : " + ex.getMessage());
        }
        return connection;
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            System.out.println("Failure to close database connections: " + ex.getMessage());
        }
    }
}
