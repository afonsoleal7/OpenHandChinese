package org.academiadecodigo.javabank.controller.transaction;

/**
 * A controller used for deposit transactions
 * @see AbstractAccountTransactionController
 */
public class DepositController extends AbstractAccountTransactionController {

    /**
     * Deposits an amount on the account with the given id
     *
     * @see AbstractAccountTransactionController#submitTransaction(int, double)
     */
    @Override
    public void submitTransaction(int accountId, double amount) {
        accountService.deposit(accountId, amount);
    }
}
