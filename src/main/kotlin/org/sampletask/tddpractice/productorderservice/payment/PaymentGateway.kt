package org.sampletask.tddpractice.productorderservice.payment

interface PaymentGateway {
    fun execute(
        totalPrice: Int,
        cardNumber: String,
    )
}
