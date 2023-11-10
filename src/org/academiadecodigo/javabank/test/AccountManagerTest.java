package org.academiadecodigo.javabank.test;

import org.academiadecodigo.javabank.managers.AccountManager;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.model.account.AccountType;
import org.academiadecodigo.javabank.model.account.SavingsAccount;

public class AccountManagerTest {

    public boolean test() {

        AccountManager accountManager = new AccountManager();
        Account ac = accountManager.openAccount(AccountType.CHECKING);
        Account as = accountManager.openAccount(AccountType.SAVINGS);

        // should be able to deposit
        accountManager.deposit(ac.getId(), 10);
        accountManager.deposit(as.getId(), SavingsAccount.MIN_BALANCE + 10);
        if (ac.getBalance() != 10 || as.getBalance() != SavingsAccount.MIN_BALANCE + 10) {
            return false;
        }

        // should be able to withdraw
        accountManager.withdraw(ac.getId(), 1);
        if (ac.getBalance() != 9) {
            return false;
        }

        // should not be able to withdraw
        accountManager.withdraw(as.getId(), 30);
        if (as.getBalance() != 110) {
            return false;
        }

        // should be able to transfer if sufficient funds are available
        accountManager.transfer(as.getId(), ac.getId(), 1);
        if (ac.getBalance() != 10 || as.getBalance() != SavingsAccount.MIN_BALANCE + 9) {
            return false;
        }

        // should not be able to transfer if available funds are not sufficient in savings
        accountManager.transfer(as.getId(), ac.getId(), 10);
        if (ac.getBalance() != 10 || as.getBalance() != SavingsAccount.MIN_BALANCE + 9) {
            return false;
        }

        // should not be able to transfer if available funds are not sufficient in checking
        accountManager.transfer(ac.getId(), as.getId(), 11);
        if (ac.getBalance() != 10 || as.getBalance() != SavingsAccount.MIN_BALANCE + 9) {
            return false;
        }

        return true;
    }
}
