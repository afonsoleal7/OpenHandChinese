package org.academiadecodigo.javabank.persistence.jdbc;

import org.academiadecodigo.javabank.persistence.TransactionManager;

public class JPATransactionManager implements TransactionManager {

    private JPASessionManager sm;

    public void setConnectionManager(JPASessionManager JDBCSessionManager) {
        this.sm = JDBCSessionManager;
    }

    public void beginRead() {
        sm.startSession();
    }

    public void beginWrite() {
        sm.getCurrentSession().getTransaction().begin();
    }


    public void commit() {
        sm.getCurrentSession().getTransaction().commit();
        sm.stopSession();
    }

    public void rollback() {
        sm.getCurrentSession().getTransaction().rollback();
        sm.stopSession();
    }
}
