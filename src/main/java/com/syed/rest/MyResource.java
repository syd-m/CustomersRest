package com.syed.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.syed.rest.entity.Customer;
import com.syed.rest.service.CustomerService;
import com.syed.rest.serviceImpl.CustomerServiceImpl;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Hello, Heroku!";
    }
    @GET
    @Path("getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> getAllCustomers() {
    		CustomerService customerService = new CustomerServiceImpl();
    		List<Customer> customers = customerService.viewAllCustomers();    		
        return customers;
    }
}
