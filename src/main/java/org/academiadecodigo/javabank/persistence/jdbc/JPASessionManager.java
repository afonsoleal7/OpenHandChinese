package org.academiadecodigo.javabank.persistence.jdbc;

import org.academiadecodigo.javabank.persistence.SessionManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPASessionManager implements SessionManager<EntityManager> {
    private EntityManager em;

    private EntityManagerFactory emf;

    /*public JDBCSessionManager(String user, String pass, String host, String database) {
        this.user = user;
        this.pass = pass;
        this.dbUrl = CONNECTOR + "//" + host + "/" + database;
    }*/

    public JPASessionManager() {
        emf = Persistence.createEntityManagerFactory("test");

    }


    @Override
    public void startSession() {
        if (em == null ){

            em = emf.createEntityManager();
        }
    }

    @Override
    public void stopSession() {
        em.close();
    }

        @Override
        public EntityManager getCurrentSession() {
        startSession();
        return em;

    }
}
