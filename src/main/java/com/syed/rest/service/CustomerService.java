package com.syed.rest.service;

import java.util.List;

import com.syed.rest.entity.Address;
import com.syed.rest.entity.Customer;
import com.syed.rest.entity.PaymentMethod;

public interface CustomerService {
	void updateAddress(Address address);

	void updateCustomer(Customer customer);

	void deleteCustomer(Long custId);

	int deletePaymentMethods(Long custId);

	Customer createCustomer(Customer customer);

	PaymentMethod updatePaymentMethod(PaymentMethod paymentMethod);

	Customer findCustomerFullData(Long custId);

	String createPaymentMethods(Long custId, List<PaymentMethod> paymentMethods);

	List<PaymentMethod> findPaymentMethods(Long customerId);

	Customer findCustomer(Long customerId);

	List<Customer> viewAllCustomers();
}
