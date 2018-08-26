package com.syed.rest.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.syed.rest.dao.CustomerDao;
import com.syed.rest.entity.Customer;

public class CustomerDaoImpl implements CustomerDao {

	public static SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

	// adding/saving customer and address to the db
	@Override
	public Customer saveCustomer(Customer customer) {
		Session session = CustomerDaoImpl.factory.openSession();
		session.beginTransaction();
		try {
			session.save(customer);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
		return customer;
	}

	@Override
	public Customer findCustomerFullData(Long custId) {
		Session session = CustomerDaoImpl.factory.openSession();
		Customer customerAllInfo = null;
		try {
			session.beginTransaction();
			customerAllInfo = session.get(Customer.class, custId);
			// Hibernate.initialize(customerAllInfo.getPaymentType());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
		return customerAllInfo;
	}

	@Override
	public void deleteCustomer(Long custId) {
		Session session = CustomerDaoImpl.factory.openSession();
		try {
			session.beginTransaction();
			Customer customer = session.load(Customer.class, custId);
			session.delete(customer);
			System.out.println("***Selected customer deleted successfully***");
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		return null;
	}

	@Override
	public Customer findCustomer(Long customerId) {
		Session session = CustomerDaoImpl.factory.openSession();
		Customer foundCustomer = new Customer();
		try {
			foundCustomer = session.get(Customer.class, customerId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
		return foundCustomer;
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<Customer> getAllCustomers() {
		Session session = CustomerDaoImpl.factory.openSession();
		// List<Customer> allCustomers = session.createCriteria(Customer.class).list();
		Query<Customer> query = session.createQuery("FROM Customer");
		List<Customer> allCustomers = query.list();
		System.out.println(allCustomers.size());
		// List<Customer> allCustomers = session.load(Customer.class);
		return allCustomers;
	}
}
