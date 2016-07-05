package com.testcompany.app.customerdata.persistence.dao;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;

import com.sun.jersey.api.NotFoundException;
import com.testcompany.app.customerdata.application.persistence.dao.CustomerDao;
import com.testcompany.app.customerdata.application.persistence.entity.Customer;

@RunWith(PowerMockRunner.class)
public class CustomerDaoTest {

    @InjectMocks
    private CustomerDao dao;

    @Before
    public void init() {
        /**
         * simulate init() during container start up
         */
        dao.init();
    }

    @Test
    public void testGetCustomerById() {
        final Customer customer = dao.getCustomerById(1L);
        assertThat(customer.getName()).isEqualTo("customer 1");
    }

    @Test(expected = NotFoundException.class)
    public void getCustomerNotExists() {
        dao.getCustomerById(122222222L);
    }

}
