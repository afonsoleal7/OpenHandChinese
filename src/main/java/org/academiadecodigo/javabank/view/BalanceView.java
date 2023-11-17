package org.academiadecodigo.javabank.view;

import org.academiadecodigo.javabank.controller.BalanceController;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;

import java.text.DecimalFormat;
import java.util.List;

/**
 * A view used to show the customer balance
 *
 * @see BalanceController
 */
public class BalanceView implements View {

    private BalanceController balanceController;
    private DecimalFormat df = new DecimalFormat("#.##");

    /**
     * Sets the controller responsible for rendering the view
     *
     * @param balanceController the controller to set
     */
    public void setBalanceController(BalanceController balanceController) {
        this.balanceController = balanceController;
    }

    /**
     * @see View#show()
     */
    @Override
    public void show() {
        showBalance();
    }

    private void showBalance() {

        Customer customer = balanceController.getCustomer();
        System.out.println("\n" + customer.getFirstName() + Messages.VIEW_BALANCE_MESSAGE + "\n");

        List<Account> accounts = customer.getAccounts();
        for (Account account : accounts) {
            System.out.println(account.getId() + "\t" + account.getAccountType() + "\t" + df.format(account.getBalance()));
        }

        System.out.println("\n\n" + Messages.VIEW_BALANCE_TOTAL_MESSAGE + df.format(balanceController.getCustomerBalance()));
    }
}
