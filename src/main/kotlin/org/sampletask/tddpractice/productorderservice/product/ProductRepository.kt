package org.sampletask.tddpractice.productorderservice.product

import org.springframework.stereotype.Repository

@Repository
class ProductRepository {
    private val persistence: MutableMap<Long, Product> = mutableMapOf()
    private var sequence: Long = 0L

    fun save(product: Product) {
        product.assignId(++sequence)
        persistence[product.id] = product
    }
}