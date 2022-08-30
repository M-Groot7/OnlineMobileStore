package com.mobile.store.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.store.dto.Payment;
import com.mobile.store.exception.PaymentException;
import com.mobile.store.service.PaymentService;

@RestController
public class PaymentController {

	@Autowired
	PaymentService paymentService;

	/**
	 * Method                       -addPayment
	 * Description                  -add payment
	 * @param payment               -pass payment details
	 * @return String               -Payment added successfully
	 * Created By                   -Nishant Kumar
	 * Creation Date                -17/Aug/2022
	 */
	@PostMapping("payment")
	public Payment addPayment(@RequestBody Payment payment) throws PaymentException {

		return this.paymentService.addPayment(payment);
	}

	/**
	 *  Method                      -getPaymentById
	 * Description                  -get payment by Id
	 * @param payment               -pass paymentId
	 * @return Payment              -Success, then showing the particular paymentId details
	 * Created By                   -Nishant Kumar
	 * Creation Date                -17/Aug/2022
	 */
	@GetMapping("payment/{paymentId}")
	public Optional<Payment> getPaymentById(@PathVariable("paymentId") Integer paymentId) throws PaymentException {
		return this.paymentService.getPaymentById(paymentId);
	}

	/**
	 *  Method                      -deletePaymentById
	 * Description                  -delete payment by Id
	 * @param payment               -pass paymentId
	 * @return Payment              -Success, then delete the payment details for any particular paymentId
	 * Created By                   -Nishant Kumar
	 * Creation Date                -17/Aug/2022
	 */
	@DeleteMapping("payment/{paymentId}")
	public Boolean deletePaymentById(@PathVariable("paymentId") Integer paymentId) throws PaymentException {
		return this.paymentService.deletePaymentById(paymentId);
	}

}
