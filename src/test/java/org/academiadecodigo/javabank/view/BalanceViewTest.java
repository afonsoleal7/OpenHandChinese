package org.academiadecodigo.javabank.view;

import org.academiadecodigo.javabank.controller.BalanceController;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

public class BalanceViewTest {

    private BalanceView view;
    private BalanceController controller;
    private Customer customer;
    private PrintStream out;
    private List<Account> accounts;

    @Before
    public void setup() {

        view = new BalanceView();
        controller = mock(BalanceController.class);
        customer = mock(Customer.class);
        out = mock(PrintStream.class);

        view.setBalanceController(controller);
        System.setOut(out);

        when(controller.getCustomer()).thenReturn(customer);
        accounts = new LinkedList<>();

        when(customer.getAccounts()).thenReturn(accounts);
    }

    @Test
    public void showBalance() {

        accounts.add(mock(Account.class));
        accounts.add(mock(Account.class));
        accounts.add(mock(Account.class));

        view.show();

        verify(controller).getCustomer();
        for (Account account : accounts) {
            verify(account).getBalance();
        }

        verify(controller).getCustomerBalance();

    }
}
