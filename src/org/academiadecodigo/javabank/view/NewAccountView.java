package org.academiadecodigo.javabank.view;

import org.academiadecodigo.javabank.controller.NewAccountController;

/**
 * A view shown at new account creation
 *
 * @see NewAccountController
 */
public class NewAccountView implements View {

    private NewAccountController newAccountController;

    /**
     * Sets the controller responsible for rendering the view
     *
     * @param newAccountController the controller to set
     */
    public void setNewAccountController(NewAccountController newAccountController) {
        this.newAccountController = newAccountController;
    }

    /**
     * @see View#show()
     */
    @Override
    public void show() {
        System.out.println("\n" + Messages.VIEW_NEW_ACCOUNT_MESSAGE + newAccountController.getNewAccountId());
    }
}
