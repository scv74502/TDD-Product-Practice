package org.sampletask.tddpractice.productorderservice.product.adapter

import org.sampletask.tddpractice.productorderservice.product.application.port.ProductPort
import org.sampletask.tddpractice.productorderservice.product.domain.Product
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class ProductAdapter(
    private val productRepository: ProductRepository,
) : ProductPort {
    override fun save(product: Product) {
        productRepository.save(product)
    }

    override fun getProduct(productId: Long): Product {
        val product =
            productRepository
                .findByIdOrNull(productId) ?: throw IllegalAccessException("상품이 존재하지 않습니다")

        return product
    }
}
