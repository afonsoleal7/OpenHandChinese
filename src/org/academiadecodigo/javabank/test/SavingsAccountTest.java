package org.academiadecodigo.javabank.test;

import org.academiadecodigo.javabank.domain.account.Account;
import org.academiadecodigo.javabank.domain.account.SavingsAccount;

public class SavingsAccountTest {

    public boolean test() {

        Account account = new SavingsAccount(1);

        // account should start with zero money
        if (account.getBalance() != 0) {
            return false;
        }

        // we should be able to deposit money in account
        account.credit(120);
        if (account.getBalance() != 120) {
            return false;
        }

        // account should maintain a minimum balance
        account.debit(40);
        if (account.getBalance() != 120) {
            return false;
        }

        // we should be able to take money from account
        account.debit(20);
        if (account.getBalance() != 100) {
            return false;
        }

        return true;
    }

}
