package org.sampletask.tddpractice.productorderservice.payment

public fun 주문결제요청_생성(): PaymentRequest {
    val orderId = 1L
    val cardNumber = "1234-1234-1234-1234"
    val request = PaymentRequest(orderId, cardNumber)
    return request
}

data class PaymentRequest(
    val orderId: Long,
    val cardNumber: String,
) {
    init {
        require(!cardNumber.isBlank())
    }
}
