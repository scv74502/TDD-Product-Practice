package org.sampletask.tddpractice.productorderservice.product.application.port

import org.sampletask.tddpractice.productorderservice.product.domain.Product

interface ProductPort {
    fun save(product: Product)

    fun getProduct(productId: Long): Product
}
