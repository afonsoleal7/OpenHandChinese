package org.academiadecodigo.javabank;

import org.academiadecodigo.javabank.controller.Controller;
import org.academiadecodigo.javabank.persistence.jdbc.JDBCSessionManager;
import org.academiadecodigo.javabank.persistence.daos.jdbc.JDBCAccountDao;
import org.academiadecodigo.javabank.persistence.daos.jdbc.JDBCCustomerDao;
import org.academiadecodigo.javabank.persistence.jdbc.JDBCTransactionManager;
import org.academiadecodigo.javabank.services.AccountServiceImpl;
import org.academiadecodigo.javabank.services.CustomerServiceImpl;
import org.academiadecodigo.javabank.services.AuthServiceImpl;

public class App {

    public static void main(String[] args) {

        App app = new App();
        app.bootStrap();
    }

    private void bootStrap() {

        JDBCSessionManager JDBCSessionManager = new JDBCSessionManager();
        JDBCTransactionManager transactionManager = new JDBCTransactionManager();
        transactionManager.setConnectionManager(JDBCSessionManager);
        JDBCAccountDao JDBCAccountDao = new JDBCAccountDao();
        JDBCCustomerDao JDBCCustomerDao = new JDBCCustomerDao();

        JDBCAccountDao.setConnectionManager(JDBCSessionManager);
        JDBCCustomerDao.setAccountDAO(JDBCAccountDao);
        JDBCCustomerDao.setConnectionManager(JDBCSessionManager);

        AccountServiceImpl accountService = new AccountServiceImpl();
        CustomerServiceImpl customerService = new CustomerServiceImpl();

        customerService.setCustomerDAO(JDBCCustomerDao);
        customerService.setTm(transactionManager);

        accountService.setAccountDAO(JDBCAccountDao);
        accountService.setTm(transactionManager);

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.setAuthService(new AuthServiceImpl());
        bootstrap.setAccountService(accountService);
        bootstrap.setCustomerService(customerService);
        Controller controller = bootstrap.wireObjects();

        // start application
        controller.init();

        JDBCSessionManager.stopSession();

    }
}
