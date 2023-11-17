package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.services.mock.MockCustomerService;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockCustomerServiceTest {

    private CustomerService customerService;

    @Before
    public void setup() {
        customerService = new MockCustomerService();
    }


    @Test
    public void testAdd() {

        // check if there are no customers
        assertEquals(0, customerService.list().size());

        // add a customer
        Customer c = new Customer();
        customerService.add(c);

        // check if it was added successfully
        assertEquals(1, customerService.list().size());

    }

    @Test
    public void testGet() {

        // check if customer one returns null
        assertNull(customerService.get(1));

        // add a customer
        Customer c = new Customer();
        customerService.add(c);

        // check if it was added successfully
        assertEquals(c, customerService.get(1));

    }

    @Test
    public void testList() {

        // check if there are no customers
        assertEquals(0, customerService.list().size());

        // add a customer
        Customer c1 = new Customer();
        customerService.add(c1);

        // check if it was added successfully
        assertEquals(1, customerService.list().size());

        // add a second customer
        Customer c2 = new Customer();
        customerService.add(c2);

        // check if it was added successfully
        assertEquals(2, customerService.list().size());

        // add a third customer
        Customer c3 = new Customer();
        customerService.add(c3);

        // create a list of customers for comparison
        List<Customer> customers = new ArrayList<>();
        customers.add(c1);
        customers.add(c2);
        customers.add(c3);

        assertEquals(customers, customerService.list());

    }

    @Test
    public void testGetBalance() {

        double a1Balance = 100;
        double a2Balance = 550;

        // create a fake customer and add it to the service
        Customer c = mock(Customer.class);
        when(c.getId()).thenReturn(1);
        customerService.add(c);

        // create two fake accounts
        Account a1 = mock(Account.class);
        Account a2 = mock(Account.class);

        // give them fake balances and ids
        when(a1.getBalance()).thenReturn(a1Balance);
        when(a1.getId()).thenReturn(1);
        when(a2.getBalance()).thenReturn(a2Balance);
        when(a2.getId()).thenReturn(2);

        // pretend these fake accounts are from the fake customer
        List<Account> accounts = new LinkedList<>();
        accounts.add(a1);
        accounts.add(a2);
        when(c.getAccounts()).thenReturn(accounts);

        // check customer balance after crediting accounts
        assertEquals(a1Balance + a2Balance, customerService.getBalance(1), 0);

    }

    @Test
    public void testListCustomerAccountIds() {

        int acc1Id = 10;
        int acc2Id = 8;

        // create a fake customer and add it to the service
        Customer c = mock(Customer.class);
        when(c.getId()).thenReturn(1);
        customerService.add(c);

        // create two fake accounts
        Account a1 = mock(Account.class);
        Account a2 = mock(Account.class);

        // give them fake ids
        when(a1.getId()).thenReturn(acc1Id);
        when(a2.getId()).thenReturn(acc2Id);

        // pretend these fake accounts are from the fake customer
        List<Account> accounts = new LinkedList<>();
        accounts.add(a1);
        accounts.add(a2);
        when(c.getAccounts()).thenReturn(accounts);

        // create a set of new customer's account ids
        Set<Integer> accountIds = new HashSet<>();
        accountIds.add(a1.getId());
        accountIds.add(a2.getId());

        assertEquals(accountIds, customerService.listCustomerAccountIds(1));
    }

}
