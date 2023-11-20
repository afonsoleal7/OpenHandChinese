package org.academiadecodigo.javabank.persistence.jdbc;

import org.academiadecodigo.javabank.persistence.SessionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCSessionManager implements SessionManager<Connection> {

    private static final String DEFAULT_USER = "root";
    private static final String DEFAULT_PASS = "";
    private static final String DEFAULT_HOST = "localhost";
    private static final String DEFAULT_DB = "jdbcbank";

    private static final String CONNECTOR = "jdbc:mysql:";

    private String dbUrl;
    private String user;
    private String pass;
    private Connection connection;

    public JDBCSessionManager(String user, String pass, String host, String database) {
        this.user = user;
        this.pass = pass;
        this.dbUrl = CONNECTOR + "//" + host + "/" + database;
    }

    public JDBCSessionManager() {
        this(DEFAULT_USER, DEFAULT_PASS, DEFAULT_HOST, DEFAULT_DB);
    }


    @Override
    public void startSession() {

        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(dbUrl, user, pass);
            }
        } catch (SQLException ex) {
            System.out.println("Failure to connect to database : " + ex.getMessage());
        }
    }

    @Override
    public void stopSession() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            System.out.println("Failure to close database connections: " + ex.getMessage());
        }
    }

    @Override
    public Connection getCurrentSession() {
        startSession();
        return connection;
    }
}
