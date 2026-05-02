package org.sampletask.tddpractice.productorderservice.order

import org.assertj.core.api.Assertions.assertThat
import org.sampletask.tddpractice.productorderservice.order.domain.Order
import org.sampletask.tddpractice.productorderservice.product.domain.DiscountPolicy
import org.sampletask.tddpractice.productorderservice.product.domain.Product
import kotlin.test.Test

class OrderTest {
    @Test
    fun getTotalTest() {
        val order = Order(Product("상품명", 1000, DiscountPolicy.NONE), 2)
        val totalPrice = order.getTotalPrice()
        assertThat(totalPrice).isEqualTo(order.product.price * order.quantity)
    }
}
