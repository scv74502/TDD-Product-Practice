package org.sampletask.tddpractice.productorderservice.order

import org.sampletask.tddpractice.productorderservice.product.Product
import org.sampletask.tddpractice.productorderservice.product.ProductRepository
import org.springframework.data.repository.findByIdOrNull

class OrderAdapter(
    val productRepository: ProductRepository,
    val orderRepository: OrderRepository
):OrderPort {
    override fun getProductById(productId: Long): Product {
        return productRepository.findByIdOrNull(productId)
            ?: throw NoSuchElementException("상품이 존재하지 않습니다")
    }

    override fun save(order: Order) {
        orderRepository.save(order)
    }
}