package org.academiadecodigo.javabank.persistence;

public interface TransactionManager {
    /**
     * Begin reading
     */
    void beginRead();

    /**
     * Begin writing
     */
    void beginWrite();

    /**
     * Commit the transaction
     */
    void commit();

    /**
     * Rollback the transaction
     */
    void rollback();
}
