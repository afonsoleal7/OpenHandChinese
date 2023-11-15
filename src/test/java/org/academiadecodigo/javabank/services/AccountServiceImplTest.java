package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.model.account.SavingsAccount;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class AccountServiceImplTest {

    private AccountService accountService;

    @Before
    public void setup() {
        accountService = new AccountServiceImpl();
    }

    @Test
    public void testAdd() {

        // add two mocked accounts to accountservice
        Account account1 = mock(Account.class);
        Account account2 = mock(Account.class);

        // account1 has no ID
        when(account1.getId()).thenReturn(null);

        // account2 has ID
        when(account2.getId()).thenReturn(5);

        accountService.add(account1);
        accountService.add(account2);

        // check if account service gave ID 1 to account1
        verify(account1, times(1)).setId(1);

        // check if account service did not give ID to account2 (as it already had)
        verify(account2, never()).setId(anyInt());

    }

    @Test
    public void testDeposit() {

        int amount = 100;

        // add a mocked account to accountservice
        Account account = mock(Account.class);
        when(account.getId()).thenReturn(1);
        accountService.add(account);

        // deposit 100
        accountService.deposit(1, amount);

        // check if the account method credit has been called with 100 as the argument
        verify(account).credit(amount);

    }

    @Test
    public void testWithdraw() {

        // add a mocked account to accountservice
        Account account1 = mock(Account.class);
        Account account2 = mock(Account.class);
        when(account1.getId()).thenReturn(1);
        when(account2.getId()).thenReturn(2);

        accountService.add(account1);
        accountService.add(account2);

        // account from which withdraw is allowed
        when(account1.canWithdraw()).thenReturn(true);
        accountService.withdraw(1, 100);
        verify(account1).debit(100);

        // account from which withdraw is not allowed
        when(account2.canWithdraw()).thenReturn(false);
        accountService.withdraw(1, 100);
        verify(account2, never()).debit(100);
    }

    @Test
    public void testTransfer() {

        int amount = 50;

        // add two mocked accounts to accountservice
        // give them a fake ID, and pretend they are debitable/creditable
        Account account1 = mock(Account.class);
        when(account1.getId()).thenReturn(1);
        when(account1.canDebit(anyDouble())).thenReturn(true);

        Account account2 = mock(Account.class);
        when(account2.getId()).thenReturn(2);
        when(account2.canCredit(anyDouble())).thenReturn(true);

        accountService.add(account1);
        accountService.add(account2);

        // transfer 50 from account1 to account2
        accountService.transfer(1, 2, amount);

        // check if debit method has been called for account1
        verify(account1).debit(amount);

        // check if credit method has been called for account2
        verify(account2).credit(amount);

    }

    @Test
    public void testTransferInsufficientFunds() {

        int amount = 50;

        // add two mocked accounts to accountservice
        // give them a fake ID, and insufficient funds for transfer
        Account account1 = mock(Account.class);
        when(account1.getId()).thenReturn(1);
        when(account1.canDebit(anyDouble())).thenReturn(false);

        Account account2 = mock(Account.class);
        when(account2.getId()).thenReturn(2);
        when(account2.canCredit(anyDouble())).thenReturn(true);

        accountService.add(account1);
        accountService.add(account2);

        // transfer 50 from account1 to account2
        accountService.transfer(1, 2, amount);

        // make sure debit method has not been called
        verify(account1, never()).debit(amount);

        // check if credit method has been called for account2
        verify(account2, never()).credit(amount);

    }
}
