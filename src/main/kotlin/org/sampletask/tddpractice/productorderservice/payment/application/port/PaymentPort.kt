package org.sampletask.tddpractice.productorderservice.payment.application.port

import org.sampletask.tddpractice.productorderservice.order.domain.Order
import org.sampletask.tddpractice.productorderservice.payment.domain.Payment

interface PaymentPort {
    fun getOrder(orderId: Long): Order

    fun pay(
        totalPrice: Int,
        cardNumber: String,
    )

    fun save(payment: Payment)
}
