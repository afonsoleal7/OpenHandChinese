package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.factories.AccountFactory;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.model.account.AccountType;
import org.academiadecodigo.javabank.services.AccountService;
import org.academiadecodigo.javabank.services.AuthService;
import org.academiadecodigo.javabank.view.View;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class NewAccountControllerTest {

    private NewAccountController newAccountController;
    private AccountFactory accountFactory;
    private AccountService accountService;
    private AuthService authService;
    private Customer customer;
    private View view;

    @Before
    public void setup() {

        newAccountController = new NewAccountController();
        accountFactory = mock(AccountFactory.class);
        accountService = mock(AccountService.class);
        authService = mock(AuthService.class);
        customer = mock(Customer.class);
        view = mock(View.class);

        // make authService mock return a mock customer
        when(authService.getAccessingCustomer()).thenReturn(customer);

        // wire them all
        newAccountController.setAccountService(accountService);
        newAccountController.setAuthService(authService);
        newAccountController.setView(view);
        newAccountController.setAccountFactory(accountFactory);

    }

    @Test
    public void initTest() {

        // fake id
        int id = 999;

        // make account factory mock return a new mock account when asked to
        Account account = mock(Account.class);
        when(account.getId()).thenReturn(id);
        when(accountFactory.createAccount(any(AccountType.class))).thenReturn(account);

        // init the controller (which creates a new account)
        newAccountController.init();

        // in the end, check if the view has been shown
        verify(view).show();

        // verify that account has been created through the account factory
        verify(accountFactory).createAccount(any(AccountType.class));

        // verify the interaction with auth service and customer
        verify(authService).getAccessingCustomer();
        verify(customer).addAccount(account);

        // check if new account ID matches mock account's ID
        assertEquals(id, (int) newAccountController.getNewAccountId());

    }
}
