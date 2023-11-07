package org.academiadecodigo.javabank.test;

import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.AccountType;
import org.academiadecodigo.javabank.managers.AccountManager;

public class BankTest {

    public boolean test() {

        AccountManager accountManager = new AccountManager();
        Bank bank = new Bank(accountManager);

        // bank initial balance should be 0
        if (bank.getBalance() != 0) {
            return false;
        }

        Customer c1 = new Customer();
        Customer c2 = new Customer();
        bank.addCustomer(c1);
        bank.addCustomer(c2);

        int a1 = c1.openAccount(AccountType.CHECKING);
        int a2 = c1.openAccount(AccountType.SAVINGS);
        int a3 = c2.openAccount(AccountType.CHECKING);
        int a4 = c2.openAccount(AccountType.SAVINGS);

        accountManager.deposit(a1, 10);
        accountManager.deposit(a2, 20);
        accountManager.deposit(a3, 30);
        accountManager.deposit(a4, 40);

        // bank balance should equal sum of all accounts
        if (bank.getBalance() != 100) {
            return false;
        }

        return true;
    }
}
