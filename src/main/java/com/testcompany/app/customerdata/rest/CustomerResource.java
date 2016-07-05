package com.testcompany.app.customerdata.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.testcompany.app.customerdata.application.persistence.entity.Customer;
import com.testcompany.app.customerdata.business.exception.ValidationException;
import com.testcompany.app.customerdata.business.service.CustomerService;

@Service
@Path("/customer")
public class CustomerResource {

    @Inject
    private CustomerService customerService;

    @Path("{customerId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveCustomer(@PathParam("customerId") final Long customerId) {
        return Response.status(Status.OK).entity(customerService.getCustomer(customerId)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCustomer(final Customer customer) {
        // validation can be done framework, e.g Bean Validation Framework (JSR303), can use Hibernate bean validation framework
        if (StringUtils.isBlank(customer.getName())) {
            throw new ValidationException("name is required");
        }

        if (StringUtils.isBlank(customer.getAddress())) {
            throw new ValidationException("address is required");
        }
        // Telephone number is optional
        return Response.status(Status.OK).entity(customerService.create(customer)).build();
    }

    @Path("{customerId}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCustomer(@PathParam("customerId") final Long customerId, final Customer customer) {
        // validation can be done framework, e.g Bean Validation Framework (JSR303), can use Hibernate bean validation framework
        if (StringUtils.isBlank(customer.getName())) {
            throw new ValidationException("name is required");
        }

        if (StringUtils.isBlank(customer.getAddress())) {
            throw new ValidationException("address is required");
        }

        customerService.update(customerId, customer);

        return Response.status(Status.NO_CONTENT).build();
    }

    @Path("{customerId}")
    @DELETE
    public Response updateCustomer(@PathParam("customerId") final Long customerId) {
        customerService.delete(customerId);
        return Response.status(Status.NO_CONTENT).build();
    }
}
