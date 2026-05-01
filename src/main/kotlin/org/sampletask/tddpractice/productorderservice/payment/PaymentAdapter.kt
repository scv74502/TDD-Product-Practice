package org.sampletask.tddpractice.productorderservice.payment

import org.sampletask.tddpractice.productorderservice.order.Order
import org.sampletask.tddpractice.productorderservice.product.DiscountPolicy
import org.sampletask.tddpractice.productorderservice.product.Product
import org.springframework.stereotype.Component

@Component
class PaymentAdapter(
    val paymentGateway: PaymentGateway,
    val paymentRepository: PaymentRepository,
) : PaymentPort {
    override fun getOrder(orderId: Long): Order = Order(Product("상품1", 1000, DiscountPolicy.NONE), 2)

    override fun pay(totalPrice: Int, cardNumber: String) {
        paymentGateway.execute(totalPrice, cardNumber)
    }

    override fun save(payment: Payment) {
        paymentRepository.save(payment)
    }
}
