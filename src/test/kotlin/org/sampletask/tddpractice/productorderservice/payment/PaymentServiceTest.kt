package org.sampletask.tddpractice.productorderservice.payment

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.sampletask.tddpractice.productorderservice.order.Order
import org.sampletask.tddpractice.productorderservice.product.DiscountPolicy
import org.sampletask.tddpractice.productorderservice.product.Product
import kotlin.text.isBlank

class PaymentServiceTest {
    lateinit var paymentService: PaymentService

    @BeforeEach
    fun setUp() {
        val paymentGateway = ConsolePaymentGateway()
        val paymentRepository = PaymentRepository()
        val paymentPort = PaymentAdapter(paymentGateway, paymentRepository)
        paymentService = PaymentService(paymentPort)
    }

    @Test
    fun `상품주문`() {
        val request = PaymentSteps.주문결제요청_생성()
        paymentService.payment(request)
    }
}
