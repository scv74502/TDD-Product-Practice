package org.sampletask.tddpractice.productorderservice.payment

class PaymentService(
    val paymentPort: PaymentPort,
) {
    fun payment(request: PaymentRequest) {
        val order = paymentPort.getOrder(request.orderId)
        val payment: Payment = Payment(order, request.cardNumber)
    }
}
