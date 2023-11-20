package org.academiadecodigo.javabank.persistence.daos;

import org.academiadecodigo.javabank.model.Customer;

import java.util.List;

public interface CustomerDao extends Dao<Customer> {

    /**
     * Gets a list of customer ids
     *
     * @return the list of customer ids
     */
    List<Integer> getCustomerIds();
}