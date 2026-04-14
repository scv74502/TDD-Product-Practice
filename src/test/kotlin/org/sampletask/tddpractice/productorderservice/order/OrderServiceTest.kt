package org.sampletask.tddpractice.productorderservice.order

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.sampletask.tddpractice.productorderservice.product.DiscountPolicy
import org.sampletask.tddpractice.productorderservice.product.Product
import org.sampletask.tddpractice.productorderservice.product.ProductAdapter
import org.sampletask.tddpractice.productorderservice.product.ProductService
import org.sampletask.tddpractice.productorderservice.product.ProductSteps
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class OrderServiceTest {
    @Autowired
    private lateinit var orderService: OrderService
    @Autowired
    private lateinit var productService: ProductService

    @Test
    fun `상품주문`() {
        productService.addProduct(ProductSteps.상품등록요청_생성())
        val request = 상품주문요청_생성()

        orderService.createOrder(request)
    }

    private fun `상품주문요청_생성`(): CreateOrderRequest {
        val productId: Long = 1L
        val quantity: Int = 1
        val orderRequest: CreateOrderRequest = CreateOrderRequest(productId, quantity)
        return orderRequest
    }
}


