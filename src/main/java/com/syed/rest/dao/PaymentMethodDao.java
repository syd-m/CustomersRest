package com.syed.rest.dao;

import java.util.List;

import com.syed.rest.entity.PaymentMethod;

public interface PaymentMethodDao {
	List<PaymentMethod> findPaymentMehods(Long custId);

	int deletePaymentMethods(Long custId);

	PaymentMethod updatePaymentMethod(PaymentMethod paymentMethod);

	void insertPaymentMethods(List<PaymentMethod> paymentMethods);
}
