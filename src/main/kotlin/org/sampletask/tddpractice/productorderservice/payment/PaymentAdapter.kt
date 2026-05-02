package org.sampletask.tddpractice.productorderservice.payment

import org.sampletask.tddpractice.productorderservice.order.Order
import org.sampletask.tddpractice.productorderservice.order.OrderRepository
import org.sampletask.tddpractice.productorderservice.product.DiscountPolicy
import org.sampletask.tddpractice.productorderservice.product.Product
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class PaymentAdapter(
    val paymentGateway: PaymentGateway,
    val paymentRepository: PaymentRepository,
    val orderRepository: OrderRepository
) : PaymentPort {
    override fun getOrder(orderId: Long): Order {
        return orderRepository.findByIdOrNull(orderId)
            ?: throw IllegalArgumentException("id가 ${orderId}인 주문을 찾을 수 없습니다.")
    }

    override fun pay(totalPrice: Int, cardNumber: String) {
        paymentGateway.execute(totalPrice, cardNumber)
    }

    override fun save(payment: Payment) {
        paymentRepository.save(payment)
    }
}
