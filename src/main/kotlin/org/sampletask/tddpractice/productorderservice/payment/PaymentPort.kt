package org.sampletask.tddpractice.productorderservice.payment

import org.sampletask.tddpractice.productorderservice.order.Order

interface PaymentPort {
    fun getOrder(orderId: Long): Order

    fun pay(
        totalPrice: Int,
        cardNumber: String,
    )

    fun save(payment: Payment)
}
