package org.academiadecodigo.javabank;

import org.academiadecodigo.javabank.controller.Controller;
import org.academiadecodigo.javabank.factories.AccountFactory;
import org.academiadecodigo.javabank.persistence.ConnectionManager;
import org.academiadecodigo.javabank.services.jdbc.JdbcAccountService;
import org.academiadecodigo.javabank.services.jdbc.JdbcCustomerService;
import org.academiadecodigo.javabank.services.AuthServiceImpl;

public class App {

    public static void main(String[] args) {

        App app = new App();
        app.bootStrap();
    }

    private void bootStrap() {

        ConnectionManager connectionManager = new ConnectionManager();

        AccountFactory accountFactory = new AccountFactory();
        JdbcAccountService accountService = new JdbcAccountService(connectionManager, accountFactory);
        JdbcCustomerService customerService = new JdbcCustomerService(connectionManager);
        customerService.setAccountService(accountService);

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.setAuthService(new AuthServiceImpl());
        bootstrap.setAccountService(accountService);
        bootstrap.setCustomerService(customerService);
        bootstrap.setAccountFactory(accountFactory);
        Controller controller = bootstrap.wireObjects();

        // start application
        controller.init();

        connectionManager.close();
    }
}
