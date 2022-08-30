package com.mobile.store.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.store.dao.PaymentRepository;
import com.mobile.store.dto.Payment;
import com.mobile.store.exception.PaymentException;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	PaymentRepository paymentRepository;

	/**
	 * Method:                         -addPayment
	 * Description:                    -Adding payment for the order which is placed successfully
	 * @param                          -Pass payment details
	 * @return String                  -Success, if payment added successfully, otherwise it throws PaymentException
	 * @throws PaymentException        -It is raised due to Payment Id already present in the database
	 * Created By                      -Nishant Kumar
	 * Creation Date					-17/AUG/2022
	 */
	@Override
	public Payment addPayment(Payment payment) throws PaymentException {
		Optional<Payment> tempPayment = this.paymentRepository.findById(payment.getPaymentId());
		if (tempPayment.isPresent())
			throw new PaymentException("payment id already exist");
		return this.paymentRepository.save(payment);
	}

	/**
	 * Method:                         -getPaymentById
	 * Description:                    -retrieving payment for the particular paymentId
	 * @param                          -Pass paymentId
	 * @return String                  -Success, then showing the particular paymentId details, otherwise it throws PaymentException
	 * @throws PaymentException        -It is raised due to Payment Id not present in the database
	 * Created By                      -Nishant Kumar
	 * Creation Date					-17/AUG/2022
	 */
	@Override
	public Optional<Payment> getPaymentById(Integer paymentId) throws PaymentException {
		Optional<Payment> tempPayment = this.paymentRepository.findById(paymentId);
		if (!tempPayment.isPresent())
			throw new PaymentException("payment id not exist");
		return this.paymentRepository.findById(paymentId);
	}
	

	/**
	 * Method:                         -deletePaymentById
	 * Description:                    -deleting payment for the particular paymentId
	 * @param                          -Pass paymentId
	 * @return String                  -Success, then deleting the particular paymentId details, otherwise it throws PaymentException
	 * @throws PaymentException        -It is raised due to Payment Id not found in the database
	 * Created By                      -Nishant Kumar
	 * Creation Date					-17/AUG/2022
	 */
	@Override
	public Boolean deletePaymentById(Integer paymentId) throws PaymentException {
		Optional<Payment> tempPayment = this.paymentRepository.findById(paymentId);
		if (!tempPayment.isPresent())
			throw new PaymentException("payment id not exist");
		this.paymentRepository.deleteById(paymentId);
		return true;
	}

}
