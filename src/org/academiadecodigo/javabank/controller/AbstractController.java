package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.view.View;

/**
 * A generic controller to be used as a base for concrete controller implementations
 * @see Controller
 */
public abstract class AbstractController implements Controller {

    protected View view;

    /**
     * Sets the controller view
     *
     * @param view the view to be set
     */
    public void setView(View view) {
        this.view = view;
    }

    /**
     * @see Controller#init()
     */
    @Override
    public void init() {
        view.show();
    }
}
