package com.syed.rest.serviceImpl;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;

import com.syed.rest.dao.CustomerDao;
import com.syed.rest.dao.PaymentMethodDao;
import com.syed.rest.daoImpl.CustomerDaoImpl;
import com.syed.rest.daoImpl.PaymentMethodDaoImpl;
import com.syed.rest.entity.Address;
import com.syed.rest.entity.Customer;
import com.syed.rest.entity.PaymentMethod;
import com.syed.rest.service.CustomerService;

@Path("order-library")
public class CustomerServiceImpl implements CustomerService {

	@Override
	public void updateAddress(Address address) {

	}

	@Override
	public void updateCustomer(Customer customer) {

	}

	@DELETE
	@Path("customers/{customerId}/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public void deleteCustomer(@PathParam("customerId") Long custId) {
		CustomerDao customerDao = new CustomerDaoImpl();
		customerDao.deleteCustomer(custId);
	}

	@Override
	public int deletePaymentMethods(Long custId) {
		PaymentMethodDao paymentMethodDao = new PaymentMethodDaoImpl();
		int updatePaymentMethods = paymentMethodDao.deletePaymentMethods(custId);
		return updatePaymentMethods;
	}

	@POST
	@Path("customers/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Customer createCustomer(Customer customer) {
		CustomerDao customerDao = new CustomerDaoImpl();
		customer.getAddress().setCustomer(customer);
		customer.setAddress(customer.getAddress());
		Customer savedCustomer = customerDao.saveCustomer(customer);
		return savedCustomer;
	}

	@GET
	@Path("customers/{customerId}/search")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Customer findCustomerFullData(@PathParam("customerId") Long custId) {
		CustomerDao customerDao = new CustomerDaoImpl();
		Customer customerFullData = customerDao.findCustomerFullData(custId);
		return customerFullData;
	}

	@Override
	public PaymentMethod updatePaymentMethod(PaymentMethod paymentMethod) {
		PaymentMethodDao paymentMethodDao = new PaymentMethodDaoImpl();
		paymentMethodDao.updatePaymentMethod(paymentMethod);
		return null;
	}

	@Override
	@POST
	@Path("customers/{customerId}/payment-method/create")
	@Consumes(MediaType.APPLICATION_JSON)
	public String createPaymentMethods(@PathParam("customerId") Long custId, List<PaymentMethod> paymentMethods) {
		if (checkIfCustomerExists(custId) != null) {
			PaymentMethodDao paymentDao = new PaymentMethodDaoImpl();
			paymentDao.insertPaymentMethods(paymentMethods);
		}
		return "Customer doesn't exists";
	}

	@GET
	@Path("customers/{customerId}/payment-methods")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public List<PaymentMethod> findPaymentMethods(@PathParam("customerId") Long customerId) {
		PaymentMethodDao paymentMethodDao = new PaymentMethodDaoImpl();
		List<PaymentMethod> customerPaymentMethods = paymentMethodDao.findPaymentMehods(customerId);
		return customerPaymentMethods;
	}

	@GET
	@Path("customers/{customerId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Customer findCustomer(@PathParam("customerId") Long customerId) {
		CustomerDao customerDao = new CustomerDaoImpl();
		Customer foundCustomer = customerDao.findCustomer(customerId);
		return foundCustomer;
	}

	@GET
	@Path("customers")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public List<Customer> viewAllCustomers() {
		CustomerDao customerDao = new CustomerDaoImpl();
		List<Customer> allCustomers = customerDao.getAllCustomers();
		return allCustomers;
	}

	private static Customer checkIfCustomerExists(Long customerId) {
		Customer checkCustomer = new Customer();
		Session session = null;
		try {
			session = CustomerDaoImpl.factory.openSession();
			checkCustomer = session.get(Customer.class, customerId);
			// Hibernate.initialize(checkCustomer.getPaymentType());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
		return checkCustomer;
	}
}
