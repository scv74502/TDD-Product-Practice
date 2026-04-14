package org.sampletask.tddpractice.productorderservice.order

import org.sampletask.tddpractice.productorderservice.product.Product

class Order(
    val product: Product,
    val quantity: Int
) {
    private var _orderId: Long = 0L

    val orderId: Long
        get() = _orderId


    init {
        require(quantity > 0) { "수량은 0보다 커야 합니다." }
    }

    fun assignId(orderId: Long){
        this._orderId = orderId
    }
}