package com.mobile.store.controller;

import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.store.dao.OrderRepository;
import com.mobile.store.dto.OrderByCustomer;
import com.mobile.store.exception.CartItemException;
import com.mobile.store.exception.CustomerException;
import com.mobile.store.exception.OrderException;
import com.mobile.store.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	OrderService orderService;
	
	/**
	 * Method                       -addOrder
	 * Description                  -To handle a HTTP POST request
	 * @param OrderByCustomer		-pass orderByCustomer
	 * @return OrderByCustomer      -Success, adding of an order. 
	 * Created By                   -Prashant Lodhi
	 * Creation Date                -19/Aug/2022
	 */
	@PostMapping("order")
	public OrderByCustomer addOrder(@RequestBody OrderByCustomer orderByCustomer) throws OrderException, CartItemException, CustomerException {
		return this.orderService.addOrder(orderByCustomer);

	}
	


	/**
	 * Method                           -getAllOrder
	 * Description                      -To handle a HTTP GET request
	 * @return List<OrderByCustomer>    -Success, adding of an order. 
	 * Created By                       -Prashant Lodhi
	 * Creation Date               	    -19/Aug/2022
	 */
	@GetMapping("getAllOrders")
	public List<OrderByCustomer> getAllOrders() throws OrderException{
		
		List<OrderByCustomer> foundAllOrders;
		foundAllOrders = this.orderService.getAllOrders();
		
		return foundAllOrders;
	}
	
	/**
	 * Method                           -getOrderById
	 * Description                      -To handle a HTTP GET request
	 * @param  Integer					-orderId
	 * @return List<OrderByCustomer>    -Success, adding of an order. 
	 * Created By                       -Prashant Lodhi
	 * Creation Date               	    -19/Aug/2022
	 */
	
	@GetMapping("order/{orderId}")
	public Optional<OrderByCustomer> getOrderById(@PathVariable("orderId") Integer orderId) throws OrderException, CartItemException {

		return this.orderService.getOrdersByOrderId1(orderId);

	}
	

}
