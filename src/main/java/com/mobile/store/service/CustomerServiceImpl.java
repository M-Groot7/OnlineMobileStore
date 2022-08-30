package com.mobile.store.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.store.dao.CartItemRepository;
import com.mobile.store.dao.CustomerRepository;
import com.mobile.store.dao.WishlistRepository;
import com.mobile.store.dto.CartItem;
import com.mobile.store.dto.Customer;
import com.mobile.store.dto.Wishlist;
import com.mobile.store.exception.CustomerException;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	WishlistRepository wishlistRepository;
	
	@Autowired
	CartItemRepository cartItemRepository;

    /**
    * Method:                         -addCustomer
    * Description:                    -Add customer in the database
    * @param customer                 -Pass customer details  
    * @return String                  -Success,if customer add successfully,otherwise it throws CustomerException.
    * @throws CustomerException :     -It is raised due to  Customer already found in the database.
    * Created By                      -Muhammad Saad
    * Created Date                    -17/Aug/2022
    */
	@Override
	public String addCustomer(Customer customer) throws CustomerException {
		Integer customerId = customer.getCustomerId();
		Optional<Customer> tempCustomer = this.customerRepository.findById(customerId);
		if (tempCustomer.isPresent()) {
			throw new CustomerException("customer is already present.");
		}
		String email = customer.getCustomerEmail();
		Optional<Customer> xCustomer = this.customerRepository.findCustomerByCustomerEmail(email);
		if(xCustomer.isPresent()) throw new CustomerException("this email id is already in use.\nPlease try with other email.");
		Customer newCustomer = this.customerRepository.save(customer);
		return "customer added sucessfully.\nUser Id is :" + newCustomer.getCustomerId();
	}

	/**
	* Method:                         -deleteCustomer
	* Description:                    -Delete customer from the database
	* @param Integer                  -Customer Id
	* @param String                   -Customer password     
	* @return String                  -Success,if customer delete successfully,otherwise it throws CustomerException.
	* @throws CustomerException :     -It is raised due to  Customer not found or old password not correct.
	* Created By                      -Muhammad Saad
	* Created Date                    -17-Aug-2022
	*/
	@Override
	public String deleteCustomerById(Integer customerId, String password) throws CustomerException {
		Optional<Customer> tempCustomer = this.customerRepository.findById(customerId);
		if (tempCustomer.isEmpty())
			throw new CustomerException("user not found.");

		Customer customer = tempCustomer.get();
		if (!(customer.getPassword().equals(password)))
			throw new CustomerException("enter correct password.");
		this.wishlistRepository.deleteFromWishlist(customerId);
		this.cartItemRepository.deleteFromCartItem(customerId);
		this.customerRepository.deleteById(customerId);
		return "customer delete sucessfully.";
	}

   /**
	* Method:                         -getCustomer
	* Description:                    -Fetch customer from the database with the help of customer's Id.
	* @param  Integer                 -Customer Id.  
	* @return Optional                -Present if customer found,otherwise it throws CustomerException.
	* @throws CustomerException :     -It is raised due to  Customer not found.
	* Created By                      -Muhammad Saad
	* Created Date                    -17-Aug-2022
	*/
	@Override
	public Optional<Customer> getCustomerById(Integer customerId) throws CustomerException {
		Optional<Customer> getCustomer = this.customerRepository.findById(customerId);
		if (getCustomer.isEmpty())
			throw new CustomerException("customer not exits");
		return getCustomer;
	}

    /**
    * Method:                         -updateCustomer
    * Description:                    -Update customer in the database
    * @param customer                 -Pass customer details  
    * @return String                  -Success,if customer update successfully,otherwise it throws CustomerException.
    * @throws CustomerException :     -It is raised due to  Customer not found.
    * Created By                      -Muhammad Saad
    * Created Date                    -17-Aug-2022
    */
	@Override
	public Customer updateCustomer(Customer customer) throws CustomerException {
		Integer customerId = customer.getCustomerId();
		Boolean result = this.customerRepository.existsById(customerId);
		if (!result)
			throw new CustomerException(
					"customer is not available in the customer list.please create account" + "first.");
		return this.customerRepository.save(customer);
	}
	
   /**
    * Method:                         -updateCustomerPhone
    * Description:                    -Update customer phone number in the database
    * @param  Integer                 -Customer Id
    * @param  String                  -Customer phone number  
    * @return String                  -Success,if customer number update successfully,otherwise it throws CustomerException.
    * @throws CustomerException :     -It is raised due to  Customer not found.
    * Created By                      -Muhammad Saad
    * Created Date                    -17-Aug-2022
    */
	@Override
	public String updateCustomerPhone(Integer customerId, String phoneNo) throws CustomerException {
		Optional<Customer> getCustomer = this.customerRepository.findById(customerId);
		if (getCustomer.isEmpty())
			throw new CustomerException("customer not exits");
		Customer foundCustomer = getCustomer.get();

		foundCustomer.setCustomerPhoneNo(phoneNo);
		this.customerRepository.save(foundCustomer);
		return "phone number update sucessfully.";
	}
	
   /**
	* Method:                         -updateCustomerAddress
	* Description:                    -Update customer address in the database
	* @param  Integer                 -Customer Id
	* @param  String                  -Customer address  
	* @return String                  -Success,if customer address update successfully,otherwise it throws CustomerException.
	* @throws CustomerException :     -It is raised due to  Customer not found.
	* Created By                      -Muhammad Saad
	* Created Date                    -17-Aug-2022
	*/
	@Override
	public String updateCustomerAddress(Integer customerId, String address) throws CustomerException {
		Optional<Customer> tempCustomer = this.customerRepository.findById(customerId);
		if (tempCustomer.isEmpty())
			throw new CustomerException("customer not found.");
		Customer customer = tempCustomer.get();
		customer.setCustomerAddress(address);
		this.customerRepository.save(customer);
		return "address update sucessfully.";
	}
	
   /**
	* Method:                         -changeCustomerPassword
	* Description:                    -Change customer password in the database
	* @param  Integer                 -Customer Id
	* @param  String                  -Customer old password
	* @param  String                  -Customer new password  
	* @return String                  -Success,if password change successfully,otherwise it throws CustomerException.
	* @throws CustomerException :     -It is raised due to  Customer not found.
	* Created By                      -Muhammad Saad
	* Created Date                    -17-Aug-2022
	*/
	@Override
	public String changeCustomerPassword(Integer customerId, String oldPassword, String newPassword)
			throws CustomerException {
		Optional<Customer> tempCustomer = this.customerRepository.findById(customerId);
		if (tempCustomer.isEmpty())
			throw new CustomerException("user not found.");

		Customer customer = tempCustomer.get();
		if (!(customer.getPassword().equals(oldPassword)))
			throw new CustomerException("enter correct existing password.");
		if (newPassword.length() < 6)
			throw new CustomerException("length of password should be more than five.");
		customer.setPassword(newPassword);
		this.customerRepository.save(customer);
		return "password update sucessfully";
	}

   /**
	* Method:                         -loginById
	* Description:                    -Login customer with the help of customer id and password
	* @param  Integer                 -Customer Id
	* @param  String                  -Customer password  
	* @return String                  -Success,if customer login successfully,otherwise it throws CustomerException.
	* @throws CustomerException :     -It is raised due to  Customer not found or password not match.
	* Created By                      -Muhammad Saad
	* Created Date                    -17-Aug-2022
	*/
	@Override
	public Customer loginById(Integer customerId, String password) throws CustomerException {
		Optional<Customer> customer = this.customerRepository.findById(customerId);
		if (customer.isEmpty())
			throw new CustomerException("user not found");
		Customer tempCustomer = customer.get();
		Customer loginCustomer = this.customerRepository.findByCustomerIdAndPassword(customerId, password);
		if (!(tempCustomer.getPassword().contentEquals(password)))
			throw new CustomerException("please enter correct password.");
		return loginCustomer;
	}
	

   /**
	* Method:                         -getCustomerSpecificDetails
	* Description:                    -Fetch customer from the database with the help of customer's Id.
	* @param  Integer                 -Customer Id.  
	* @return Customer                -Customer if customer found,otherwise it throws CustomerException.
	* @throws CustomerException :     -It is raised due to  Customer not found.
	* Created By                      -Muhammad Saad
	* Created Date                    -17-Aug-2022
	*/
	@Override
	public Customer getCustomerSpecificDetails(Integer customerId) throws CustomerException {
		Optional<Customer> tempCustomer = this.customerRepository.findBycusotmerId(customerId);
		if (tempCustomer.isEmpty())
			throw new CustomerException("user not found");
		Customer customer = tempCustomer.get();
		return customer;
	}
	
	
   /**
	* Method:                         -countCustomer
	* Description:                    -Count total number of customers found in the database
	* @return Long                    -Count,total number of customers,otherwise it throws CustomerException.
	* @throws CustomerException :     -It is raised due to  Customer not found.
	* Created By                      -Muhammad Saad
	* Created Date                    -17-Aug-2022
	*/
	@Override
	public Long countCustomers() throws CustomerException {
		Long count = this.customerRepository.count();
		if (count <= 0)
			throw new CustomerException("customer is not added.Please add customers.");
		return count;
	}
	
   /**
	* Method:                         -findAllCustomers
	* Description:                    -Find all customers present in the database
	* @return List                    -Customer,all customers,otherwise it throws CustomerException.
	* @throws CustomerException :     -It is raised due to  Customer not found.
	* Created By                      -Muhammad Saad
	* Created Date                    -17-Aug-2022
	*/
	@Override
	public List<Customer> findAllCustomers() throws CustomerException {
		List<Customer> tempCustomer = this.customerRepository.findAll();
		if (tempCustomer.isEmpty())
			throw new CustomerException("customers not found.Add new customer.");
		return this.customerRepository.findAll();
	}

   /**
	* Method:                         -findByCustomerEmail
	* Description:                    -Fetch customer from the database with the help of customer's email.
	* @param  String                  -Customer email.  
	* @return Optional                -Present,if customer found,otherwise it throws CustomerException.
	* @throws CustomerException :     -It is raised due to  Customer not found.
	* Created By                      -Muhammad Saad
	* Created Date                    -17-Aug-2022
	*/
	@Override
	public Customer findByCustomerEmail(String email) throws CustomerException {
		Optional<Customer> getCustomer = this.customerRepository.findCustomerByCustomerEmail(email);
		if (getCustomer.isEmpty())
			throw new CustomerException("customer not exits");
		Customer newCustomer = getCustomer.get();
		List<Wishlist> wishlist = this.wishlistRepository.findWishlistByCustomerIdOrderByDateDesc(newCustomer.getCustomerId());
		newCustomer.setWishlist(wishlist);
		//List<CartItem> cartItem = this.cartItemRepository.findCartitemsByCustomerId(newCustomer.getCustomerId());
		//newCustomer.setCartItem(cartItem);
		return newCustomer;
	}

}
