package org.sampletask.tddpractice.productorderservice.payment.adatper

interface PaymentGateway {
    fun execute(
        totalPrice: Int,
        cardNumber: String,
    )
}
