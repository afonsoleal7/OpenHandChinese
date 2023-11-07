package org.academiadecodigo.javabank.test;

import org.academiadecodigo.javabank.domain.account.Account;
import org.academiadecodigo.javabank.domain.account.AccountType;
import org.academiadecodigo.javabank.managers.AccountManager;

public class AccountManagerTest {

    public boolean test() {

        AccountManager accountManager = new AccountManager();

        // should be able to open a new checking account
        Account a1 = accountManager.openAccount(AccountType.CHECKING);
        if (a1.getBalance() != 0) {
            return false;
        }

        // should be able to open a new savings account
        Account a2 = accountManager.openAccount(AccountType.SAVINGS);
        if (a2.getBalance() != 0) {
            return false;
        }

        // should be able to deposit money on accounts
        accountManager.deposit(a1.getId(), 100);
        accountManager.deposit(a2.getId(), 120);
        if (a1.getBalance() != 100 || a2.getBalance() != 120) {
            return false;
        }

        // should withdraw money from checking account
        accountManager.withdraw(a1.getId(), 50);
        if (a1.getBalance() != 50) {
            return false;
        }

        // should not withdraw money from savings account
        accountManager.withdraw(a2.getId(), 10);
        if (a2.getBalance() != 120) {
            return false;
        }

        // should be able to transfer money between accounts
        accountManager.transfer(a2.getId(), a1.getId(), 10);
        if (a1.getBalance() != 60 || a2.getBalance() != 110) {
            return false;
        }

        // should make sure savings account does not go bellow minimum balance
        accountManager.transfer(a2.getId(), a1.getId(), 11);
        if (a1.getBalance() != 60 || a2.getBalance() != 110) {
            return false;
        }

        return true;
    }
}
