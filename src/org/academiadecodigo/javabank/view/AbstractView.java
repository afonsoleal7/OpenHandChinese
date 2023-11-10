package org.academiadecodigo.javabank.view;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.model.Bank;

/**
 * A generic view to be used as a base for concrete view implementations
 * @see View
 */
public abstract class AbstractView implements View {

    protected Prompt prompt;
    protected Bank bank;

    /**
     * Sets the prompt used for the UI
     *
     * @param prompt the prompt to set
     */
    public void setPrompt(Prompt prompt) {
        this.prompt = prompt;
    }

    /**
     * Sets the bank
     *
     * @param bank the bank to set
     */
    public void setBank(Bank bank) {
        this.bank = bank;
    }
}
