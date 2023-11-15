package org.academiadecodigo.javabank.view;

import org.academiadecodigo.javabank.controller.BalanceController;
import org.academiadecodigo.javabank.controller.NewAccountController;
import org.academiadecodigo.javabank.controller.transaction.DepositController;
import org.academiadecodigo.javabank.controller.transaction.WithdrawalController;

/**
 * The possible operations types
 */
public enum UserOptions {

    /**
     * @see BalanceController
     */
    GET_BALANCE(1, Messages.MENU_GET_BALANCE),

    /**
     * @see DepositController
     */
    DEPOSIT(2, Messages.MENU_DEPOSIT),

    /**
     * @see WithdrawalController
     */
    WITHDRAW(3, Messages.MENU_WITHDRAW),

    /**
     * @see NewAccountController
     */
    OPEN_ACCOUNT(4, Messages.MENU_OPEN_ACCOUNT),

    /**
     * User option to quit the application
     */
    QUIT(5, Messages.MENU_QUIT);

    private int option;
    private String message;

    UserOptions(int option, String message) {
        this.option = option;
        this.message = message;
    }

    /**
     * Gets the user option number
     *
     * @return the option number
     */
    public int getOption() {
        return option;
    }

    /**
     * Gets the message for the user option
     *
     * @return the user option message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets the messages for the options the user can perform
     *
     * @return an array containing all the messages
     */
    public static String[] getMessages() {

        String[] messages = new String[values().length];

        for (UserOptions option : values()) {
            messages[option.getOption() - 1] = option.getMessage();
        }

        return messages;
    }
}
