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

    @Test
    fun none_discounted_product() {
        val product = Product("상품명", 1000, DiscountPolicy.NONE)
        val discountedPrice = product.getDiscountedPrice()
        assertThat(discountedPrice).isEqualTo(product.price)
    }

    @Test
    fun fix_1000_discounted_product() {
        val product = Product("상품명", 1000, DiscountPolicy.FIX_1000_DISCOUNT)
        val discountedPrice = product.getDiscountedPrice()
        assertThat(discountedPrice).isEqualTo(product.price - 1000)
    }

    @Test
    fun over_discounted_product() {
        val product = Product("상품명", 500, DiscountPolicy.FIX_1000_DISCOUNT)
        val discountedPrice = product.getDiscountedPrice()
        assertThat(discountedPrice).isEqualTo(0)
    }
}
