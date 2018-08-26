package com.syed.rest.dao;

import com.syed.rest.entity.Address;

public interface AddressDao {
	Address updateAddress(Address address);

	int deleteAddress(Long custId);
}
