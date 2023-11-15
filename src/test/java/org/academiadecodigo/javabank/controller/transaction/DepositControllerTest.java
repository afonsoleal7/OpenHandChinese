package org.academiadecodigo.javabank.controller.transaction;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.services.AccountService;
import org.academiadecodigo.javabank.services.AuthService;
import org.academiadecodigo.javabank.services.CustomerService;
import org.academiadecodigo.javabank.view.View;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class DepositControllerTest {

    private DepositController depositController;
    private AuthService authService;
    private AccountService accountService;
    private CustomerService customerService;
    private View view;

    @Before
    public void setup() {

        view = mock(View.class);
        authService = mock(AuthService.class);
        accountService = mock(AccountService.class);
        customerService = mock(CustomerService.class);

        depositController = new DepositController();
        depositController.setCustomerService(customerService);
        depositController.setAccountService(accountService);
        depositController.setAuthService(authService);
        depositController.setView(view);

    }

    @Test
    public void initTest() {

        depositController.init();
        verify(view).show();

    }

    @Test
    public void getAccountIdsTest() {

        // fake customer
        int fakeId = 998;
        Set<Integer> fakeAccountIds = new HashSet<>();
        Customer customer = mock(Customer.class);
        when(authService.getAccessingCustomer()).thenReturn(customer);
        when(customer.getId()).thenReturn(fakeId);
        when(customerService.listCustomerAccountIds(fakeId)).thenReturn(fakeAccountIds);

        assertEquals(depositController.getAccountIds(), fakeAccountIds);

    }

    @Test
    public void submitTransactionTest() {

        int fakeId = 999;
        double fakeAmount = 10.5;

        depositController.submitTransaction(fakeId, fakeAmount);

        verify(accountService).deposit(fakeId, fakeAmount);

    }
}
