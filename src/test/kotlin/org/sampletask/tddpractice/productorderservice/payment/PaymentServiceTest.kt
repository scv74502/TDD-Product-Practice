package org.sampletask.tddpractice.productorderservice.payment

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.sampletask.tddpractice.productorderservice.order.Order
import org.sampletask.tddpractice.productorderservice.order.OrderService
import org.sampletask.tddpractice.productorderservice.order.OrderSteps
import org.sampletask.tddpractice.productorderservice.product.DiscountPolicy
import org.sampletask.tddpractice.productorderservice.product.Product
import org.sampletask.tddpractice.productorderservice.product.ProductService
import org.sampletask.tddpractice.productorderservice.product.ProductSteps
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.text.isBlank

@SpringBootTest
class PaymentServiceTest {
    @Autowired
    lateinit var paymentService: PaymentService

    @Autowired
    lateinit var productService: ProductService

    @Autowired
    lateinit var orderService: OrderService

    @Test
    fun `상품주문`() {
        productService.addProduct(ProductSteps.상품등록요청_생성())
        orderService.createOrder(OrderSteps.상품주문요청_생성())
        val request = PaymentSteps.주문결제요청_생성()

        paymentService.payment(request)
    }
}
