package org.sampletask.tddpractice.productorderservice.product

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class ProductService(
    private val productPort: ProductPort,
) {
    @Transactional
    fun addProduct(request: AddProductRequest) {
        val product =
            Product(
                name = request.name,
                price = request.price,
                discountPolicy = request.discountPolicy,
            )
        productPort.save(product)
    }
}
