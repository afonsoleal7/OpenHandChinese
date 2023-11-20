package org.academiadecodigo.javabank.persistence;

public interface SessionManager<T> {
    /**
     * Starts the session
     */
    void startSession();

    /**
     * Stops the session
     */
    void stopSession();

    /**
     * Gets the current session
     *
     * @return the current session
     */
    T getCurrentSession();
}
