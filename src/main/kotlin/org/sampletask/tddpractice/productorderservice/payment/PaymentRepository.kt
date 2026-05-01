package org.sampletask.tddpractice.productorderservice.payment

import org.springframework.stereotype.Repository

@Repository
class PaymentRepository {
    var persistence: MutableMap<Long, Payment> = mutableMapOf()
    var sequence = 0L

    fun save(payment: Payment) {
        payment.assignId(++sequence)
        persistence[payment.id] = payment
    }
}
