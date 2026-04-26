package org.sampletask.tddpractice.productorderservice.product

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DiscountPolicyTest {
    @Test
    fun noneDiscountPolicy() {
        val price = 1000
        val discountPolicy = DiscountPolicy.NONE.applyDiscount(price)
        assertThat(discountPolicy).isEqualTo(price)
    }

    @Test
    fun fix100DiscountPolicy() {
        val price = 1000
        val discountPolicy = DiscountPolicy.FIX_1000_DISCOUNT.applyDiscount(price)
        assertThat(discountPolicy).isEqualTo(price - 1000)
    }
}
