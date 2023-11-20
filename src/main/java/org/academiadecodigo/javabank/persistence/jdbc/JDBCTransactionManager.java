package org.academiadecodigo.javabank.persistence.jdbc;

import org.academiadecodigo.javabank.persistence.TransactionManager;

import java.sql.SQLException;

public class JDBCTransactionManager implements TransactionManager {

    private JDBCSessionManager sm;

    public void setConnectionManager(JDBCSessionManager JDBCSessionManager) {
        this.sm = JDBCSessionManager;
    }

    public void beginRead() {
            sm.startSession();
    }

    public void beginWrite() {
        try {
            sm.getCurrentSession().setAutoCommit(false);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void commit() {

        try {
            if (!sm.getCurrentSession().getAutoCommit()) {
                sm.getCurrentSession().commit();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        sm.stopSession();
    }

    public void rollback() {

        try {
            if (!sm.getCurrentSession().getAutoCommit()) {
                sm.getCurrentSession().rollback();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        sm.stopSession();
    }
}
