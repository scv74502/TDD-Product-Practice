package org.sampletask.tddpractice.productorderservice.order.adapter

import org.sampletask.tddpractice.productorderservice.order.application.port.OrderPort
import org.sampletask.tddpractice.productorderservice.order.domain.Order
import org.sampletask.tddpractice.productorderservice.product.adapter.ProductRepository
import org.sampletask.tddpractice.productorderservice.product.domain.Product
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class OrderAdapter(
    val productRepository: ProductRepository,
    val orderRepository: OrderRepository,
) : OrderPort {
    override fun getProductById(productId: Long): Product =
        productRepository.findByIdOrNull(productId)
            ?: throw NoSuchElementException("상품이 존재하지 않습니다")

    override fun save(order: Order) {
        orderRepository.save(order)
    }
}
