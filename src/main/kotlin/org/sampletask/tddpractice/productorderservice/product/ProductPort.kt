package org.sampletask.tddpractice.productorderservice.product

interface ProductPort {
    fun save(product: Product)

    fun getProduct(productId: Long): Product
}
