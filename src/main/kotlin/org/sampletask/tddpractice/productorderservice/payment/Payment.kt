package org.sampletask.tddpractice.productorderservice.payment

import org.sampletask.tddpractice.productorderservice.order.Order

class Payment(
    val order: Order,
    val cardNumber: String,
) {
    var id: Long = 0
        private set

    init {
        require(!cardNumber.isBlank())
    }

    fun assignId(id: Long) {
        this.id = id
    }

    fun getTotalPrice(): Int {
        return order.getTotalPrice()
    }
}
