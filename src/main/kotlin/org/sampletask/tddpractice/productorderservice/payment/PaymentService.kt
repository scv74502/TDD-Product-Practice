package org.sampletask.tddpractice.productorderservice.payment

import org.springframework.stereotype.Component

@Component
class PaymentService(
    val paymentPort: PaymentPort,
) {
    fun payment(request: PaymentRequest) {
        val order = paymentPort.getOrder(request.orderId)
        val payment: Payment = Payment(order, request.cardNumber)

        paymentPort.pay(payment.getTotalPrice(), payment.cardNumber)
        paymentPort.save(payment)
    }
}
