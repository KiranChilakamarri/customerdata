package com.testcompany.app.customerdata.business.service;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.testcompany.app.customerdata.application.persistence.dao.CustomerDao;
import com.testcompany.app.customerdata.application.persistence.entity.Customer;

/**
 * Just to demonstrate the service lay which will process business logic here
 *
 */
@Component
public class CustomerService {

    @Inject
    private CustomerDao customerDao;

    public Customer getCustomer(final Long customerId) {
        return customerDao.getCustomerById(customerId);
    }

    public Customer create(final Customer customer) {

        final Long customerId = customerDao.add(customer);
        customer.setId(customerId);

        return customer;
    }

    public void update(final Long customerId, final Customer newCustomer) {
        customerDao.update(customerId, newCustomer);
    }

    public void delete(final Long customerId) {
        customerDao.delete(customerId);

    }

}
