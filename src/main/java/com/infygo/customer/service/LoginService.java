/**
 * 
 */
package com.infygo.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infygo.customer.dto.LoginDetails;
import com.infygo.customer.entity.Customer;
import com.infygo.customer.exception.ExceptionConstants;
import com.infygo.customer.exception.InfyGoServiceException;
import com.infygo.customer.repository.CustomerRepository;



/**
 * The Class AadharService.
 */
@Service
public class LoginService {

	@Autowired
	private CustomerRepository customerRepository;

	public boolean isUserValid(LoginDetails customerLogin) throws InfyGoServiceException {

		Customer customer = customerRepository.findById(customerLogin.getUserId()).get();

		if (customer == null) {
			throw new InfyGoServiceException(ExceptionConstants.USER_NOT_FOUND.toString(), "User record not found");
		} else if (!(customer.getPassword().equals(customerLogin.getPassword()))) {
			throw new InfyGoServiceException(ExceptionConstants.USER_INVALID.toString(), "Invalid credentials");
		}
		return true;

	}

}
