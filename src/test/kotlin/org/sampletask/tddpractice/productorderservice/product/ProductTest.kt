package org.sampletask.tddpractice.productorderservice.product

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ProductTest {
    @Test
    fun update() {
        val product = Product("상품명", 1000, DiscountPolicy.NONE)

        val changedName = "상품 수정"
        val changedPrice = 2000
        product.update(changedName, changedPrice, DiscountPolicy.NONE)
        assertThat(product.name).isEqualTo(changedName)
        assertThat(product.price).isEqualTo(changedPrice)
    }
}
