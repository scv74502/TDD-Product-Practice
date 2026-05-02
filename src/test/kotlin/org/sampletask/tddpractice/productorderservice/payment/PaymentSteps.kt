package org.sampletask.tddpractice.productorderservice.payment

import org.sampletask.tddpractice.productorderservice.payment.application.service.PaymentRequest

public class PaymentSteps {
    companion object {
        fun `주문결제요청_생성`(): PaymentRequest {
            val orderId = 1L
            val cardNumber = "1234-1234-1234-1234"
            val request = PaymentRequest(orderId, cardNumber)
            return request
        }
    }
}

public fun 주문결제요청_생성(): PaymentRequest {
    val orderId = 1L
    val cardNumber = "1234-1234-1234-1234"
    val request = PaymentRequest(orderId, cardNumber)
    return request
}
