package org.sampletask.tddpractice.productorderservice.product.domain

import kotlin.math.max

enum class DiscountPolicy {
    NONE {
        override fun applyDiscount(price: Int): Int = price
    },
    FIX_1000_DISCOUNT {
        override fun applyDiscount(price: Int): Int = max(price - 1000, 0)
    }, ;

    abstract fun applyDiscount(price: Int): Int
}
