package org.academiadecodigo.javabank.controller.transaction;

import org.academiadecodigo.javabank.controller.Controller;

/**
 * Common interface for account transaction controllers,
 * provides a method to perform account transactions
 */
public interface AccountTransactionController extends Controller {

    /**
     * Perform an account transaction
     *
     * @param accountId the destination account id
     * @param amount the amount of the transaction
     */
    void submitTransaction(int accountId, double amount);
}
