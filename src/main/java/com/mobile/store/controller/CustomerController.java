package com.mobile.store.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.store.dto.Customer;
import com.mobile.store.exception.CartItemException;
import com.mobile.store.exception.CustomerException;
import com.mobile.store.exception.ProductException;
import com.mobile.store.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
    /**
    * Method:                         -addCustomer
    * Description:                    -adding customer
    * @param customer                 -Pass customer details  
    * @return String                  -customer added sucessfully
    * Created By                      -Muhammad Saad
    * Created Date                    -18/Aug/2022
    */	
	@PostMapping("customer")
	public String addCustomer(@Valid @RequestBody Customer customer) throws CustomerException {
		return this.customerService.addCustomer(customer);
	}

    /**
    * Method:                         -deleteCustomerById
    * Description:                    -delete customer
    * @param customerId               -customer Id
    * @param password				  -customer password
    * @return String                  -customer delete sucessfully
    * Created By                      -Muhammad Saad
    * Created Date                    -18/Aug/2022
    */
	@DeleteMapping("customer/delete/{customerId}/{password}")
	public String deleteCustomerById(@PathVariable("customerId") Integer customerId,
			@PathVariable("password") String password) throws CustomerException {
		return this.customerService.deleteCustomerById(customerId, password);
	}

    /**
    * Method:                         -updatePhoneNoById
    * Description:                    -update customer phone number
    * @param customerId               -customer Id
    * @param newPhoneNo				  -customer phone number
    * @return String                  -customer update sucessfully
    * Created By                      -Muhammad Saad
    * Created Date                    -18/Aug/2022
    */
	@PutMapping("customer/update/phoneNo/{customerId}/{newPhoneNo}")
	public String updatePhoneNoById(@Valid @PathVariable("customerId") Integer customerId,
			@Valid @PathVariable("newPhoneNo") String newPhoneNo) throws CustomerException {
		return this.customerService.updateCustomerPhone(customerId, newPhoneNo);
	}

    /**
    * Method:                         -updateCustomerAddress
    * Description:                    -update customer address
    * @param customerId               -customer Id
    * @param newAddress 			  -customer address
    * @return String                  -customer update sucessfully
    * Created By                      -Muhammad Saad
    * Created Date                    -18/Aug/2022
    */
	@PutMapping("customer/update/address/{customerId}/{newAddress}")
	public String updateCustomerAddress(@Valid @PathVariable("customerId") Integer customerId,
			@Valid @PathVariable("newAddress") String address) throws CustomerException {
		return this.customerService.updateCustomerAddress(customerId, address);

	}

    /**
    * Method:                         -changeCustomerPassword
    * Description:                    -change customer password
    * @param customerId               -customer Id
    * @param oldPassword			  -customer old password
    * @param newPassword          	  -customer new password
    * @return String                  -customer update sucessfully
    * Created By                      -Muhammad Saad
    * Created Date                    -18/Aug/2022
    */
	@PutMapping("customer/update/password/{customerId}/{oldPassword}/{newPassword}")
	public String changeCustomerPassword(@Valid @PathVariable Integer customerId,@Valid @PathVariable String oldPassword,
			@Valid @PathVariable String newPassword) throws CustomerException {
		return this.customerService.changeCustomerPassword(customerId, oldPassword, newPassword);
	}

    /**
    * Method:                         -getCustomerSpe
    * Description:                    -get customer
    * @param customerId               -customer Id
    * @return Customer                -Customer
    * Created By                      -Muhammad Saad
    * Created Date                    -18/Aug/2022
    */
	@GetMapping("customer/{customerId}")
	public Customer getCustomerSpe(@PathVariable("customerId") Integer Id) throws CustomerException {
		return this.customerService.getCustomerSpecificDetails(Id);
	}

    /**
    * Method:                         -countCustomers
    * Description:                    -count total customer
    * @return Long                    -total customers count
    * Created By                      -Muhammad Saad
    * Created Date                    -18/Aug/2022
    */
	@GetMapping("customer/count")
	public Long countCustomers() throws CustomerException {
		return this.customerService.countCustomers();
	}

    /**
    * Method:                         -findAllCustomers
    * Description:                    -find all customer
    * @return List                    -Customer
    * Created By                      -Muhammad Saad
    * Created Date                    -18/Aug/2022
    */
	@GetMapping("all-customer")
	public List<Customer> findAllCustomers() throws CustomerException {
		return this.customerService.findAllCustomers();
	}

    /**
    * Method:                         -getCustomerByEmail
    * Description:                    -get customer by email
    * @param String					  -Customer Email
    * @return Optional                -Customer
    * Created By                      -Muhammad Saad
    * Created Date                    -18/Aug/2022
     * @throws ProductException 
     * @throws CartItemException 
    */
	@GetMapping("customer-email/{email}")
	public Customer getCustomerByEmail(@PathVariable("email") String email) throws CustomerException, CartItemException, ProductException {
		return this.customerService.findByCustomerEmail(email);
	}
}
