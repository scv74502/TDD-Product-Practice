package org.sampletask.tddpractice.productorderservice.payment.application.service

data class PaymentRequest(
    val orderId: Long,
    val cardNumber: String,
) {
    init {
        require(!cardNumber.isBlank())
    }
}
