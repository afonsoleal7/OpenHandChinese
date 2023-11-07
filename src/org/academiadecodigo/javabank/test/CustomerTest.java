package org.academiadecodigo.javabank.test;

import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.AccountType;
import org.academiadecodigo.javabank.managers.AccountManager;

public class CustomerTest {

    public boolean test() {

        AccountManager accountManager = new AccountManager();
        Customer customer = new Customer();
        customer.setAccountManager(accountManager);

        // customer should start with zero balance
        if (customer.getBalance() != 0) {
            return false;
        }

        int a1 = customer.openAccount(AccountType.CHECKING);
        int a2 = customer.openAccount(AccountType.SAVINGS);
        accountManager.deposit(a1, 100);
        accountManager.deposit(a2, 120);

        // customer balance should equal sum of all accounts balance
        if (customer.getBalance() != 220) {
            return false;
        }

        // customer should be able to get balance for its accounts
        if (customer.getBalance(a1) != 100 || customer.getBalance(a2) != 120) {
            return false;
        }

        return true;
    }
}
