package org.sampletask.tddpractice.productorderservice.order.application.port

import org.sampletask.tddpractice.productorderservice.order.domain.Order
import org.sampletask.tddpractice.productorderservice.product.domain.Product

interface OrderPort {
    fun getProductById(productId: Long): Product

    fun save(order: Order)
}
