package org.sampletask.tddpractice.productorderservice.order

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.sampletask.tddpractice.productorderservice.product.DiscountPolicy
import org.sampletask.tddpractice.productorderservice.product.Product

class OrderServiceTest {
    private lateinit var orderService: OrderService
    private lateinit var orderPort: OrderPort

    @BeforeEach
    fun setUp() {
        val orderRepository = OrderRepository()
        orderPort = object : OrderPort {
            override fun getProductById(productId: Long): Product {
                return Product("상품명", 1000, DiscountPolicy.NONE)
            }

            override fun save(order: Order) {
                orderRepository.save(order)
            }
        }
        orderService = OrderService(orderPort)
    }

    @Test
    fun `상품주문`() {
        val request: CreateOrderRequest = `상품주문요청_생성`()
        orderService.createOrder(request);
    }

    private fun `상품주문요청_생성`(): CreateOrderRequest {
        val productId: Long = 1L
        val quantity: Int = 1
        val orderRequest: CreateOrderRequest = CreateOrderRequest(productId, quantity)
        return orderRequest
    }
}


