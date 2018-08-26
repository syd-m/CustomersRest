package com.syed.rest.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.syed.rest.dao.PaymentMethodDao;
import com.syed.rest.entity.PaymentMethod;

@SuppressWarnings("deprecation")
public class PaymentMethodDaoImpl implements PaymentMethodDao {

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<PaymentMethod> findPaymentMehods(Long custId) {
		Session session = CustomerDaoImpl.factory.openSession();
		List<PaymentMethod> customerPaymentMethods = null;
		try {
			Query<PaymentMethod> query = session.createQuery("FROM PaymentMethod where customer_id=:customer_id");
			query.setParameter("customer_id", custId);
			customerPaymentMethods = query.list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			session.close();
		}
		return customerPaymentMethods;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int deletePaymentMethods(Long custId) {
		Session session = CustomerDaoImpl.factory.openSession();
		session.beginTransaction();
		Query<PaymentMethod> query = session.createQuery("delete PaymentMethod where customer_id = :customer_id");
		query.setParameter("customer_id", custId);
		int updatedRows = query.executeUpdate();
		return updatedRows;
	}

	@Override
	public PaymentMethod updatePaymentMethod(PaymentMethod paymentMethod) {
		Session session = CustomerDaoImpl.factory.openSession();
		session.beginTransaction();
		try {
			session.saveOrUpdate(paymentMethod);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			session.close();
		}
		return null;
	}

	@Override
	public void insertPaymentMethods(List<PaymentMethod> paymentMethods) {
		Session session = CustomerDaoImpl.factory.openSession();
		session.beginTransaction();
		try {
			for (PaymentMethod paymentMethod : paymentMethods) {
				session.save(paymentMethod);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			session.close();
		}
	}

}
