package org.sampletask.tddpractice.productorderservice.order

import org.sampletask.tddpractice.productorderservice.product.Product

interface OrderPort {
    fun getProductById(productId: Long): Product

    fun save(order: Order)
}
