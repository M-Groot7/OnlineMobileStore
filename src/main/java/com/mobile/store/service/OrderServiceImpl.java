package com.mobile.store.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.store.dao.CartItemRepository;
import com.mobile.store.dao.CustomerRepository;
import com.mobile.store.dao.OrderRepository;
import com.mobile.store.dto.CartItem;
import com.mobile.store.dto.Customer;
import com.mobile.store.dto.OrderByCustomer;
import com.mobile.store.exception.CartItemException;
import com.mobile.store.exception.CustomerException;
import com.mobile.store.exception.OrderException;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;

	
	/**
	    * Method:                         -addOrder
	    * Description:                    -Add order in the database
	    * @param orderByCustomer          -Pass order details  
	    * @return String                  -Success,if order add successfully,otherwise it throws CustomerException.
	    * @throws OrderException :        -It is raised due to  Either the cart or the user  not found in the database.
	    * Created By                      -Prashant Lodhi
	    * Created Date                    -18/Aug/2022
	*/

	@Override
	public OrderByCustomer addOrder(OrderByCustomer orderByCustomer) throws OrderException, CartItemException, CustomerException {
		Integer customerId = orderByCustomer.getCustomerId();
		Optional<Customer> customer = this.customerRepository.findBycusotmerId(customerId);
		if (customer.isEmpty())
			throw new CustomerException("user not found.");
		List<CartItem> cartItem = this.cartItemRepository.findCartitemsByCustomerId(customerId);
		if (cartItem.isEmpty())
			throw new CartItemException("Cart Not Found");
		
		OrderByCustomer temp =  this.orderRepository.save(orderByCustomer);
		
		temp.setCartItem(cartItem);
		return this.orderRepository.save(temp);
		
		
	}
	
	/**
		* Method:                         -getOrderByOrderId1
		* Description:                    -Fetch order from the database with the help of order Id.
		* @param  Integer                 -Order Id.  
		* @return Optional                -Present if order found,otherwise it throws OrderException.
		* @throws OrderException :     -It is raised due to  Customer not found.
		* Created By                      -Prashant Lodhi
		* Created Date                    -17-Aug-2022
	*/
	@Override
	public Optional<OrderByCustomer> getOrdersByOrderId1(Integer orderId) throws OrderException, CartItemException {
		Optional<OrderByCustomer> tempOrder = this.orderRepository.findById(orderId);
		if (tempOrder.isEmpty()) {
			throw new OrderException("order not found");
		}
		return tempOrder;
//		return this.orderRepository.findById(orderId);
	}


	
	/**
	* Method:                         -getAllOrder
	* Description:                    -Find all Orders present in the database
	* @return List                    -Orders all orders,otherwise it throws OrderException.
	* @throws OrderException :        -It is raised due to  Order not found.
	* Created By                      -Prashant Lodhi
	* Created Date                    -17-Aug-2022
	*/
	@Override
	public List<OrderByCustomer> getAllOrders() throws OrderException {
		List<OrderByCustomer> allOfOrders = this.orderRepository.findAll();
		if (allOfOrders.isEmpty()) {
			throw new OrderException("No orders found");
		}
		else {
			return allOfOrders;
		}
	}
	

}