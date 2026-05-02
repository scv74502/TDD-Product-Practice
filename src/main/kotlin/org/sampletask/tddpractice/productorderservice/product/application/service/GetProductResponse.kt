package org.sampletask.tddpractice.productorderservice.product.application.service

import org.sampletask.tddpractice.productorderservice.product.domain.DiscountPolicy
import org.springframework.util.Assert

data class GetProductResponse(
    val id: Long,
    val name: String,
    val price: Int,
    val discountPolicy: DiscountPolicy,
) {
    init {
        Assert.hasText(name, "상품명은 필수입니다.")
        Assert.isTrue(price > 0, "상품 가격은 0보다 커야 합니다.")

//            // 코틀린 친화적 문법
//            require(name.isNotBlank()) { "상품명은 필수입니다." }
//            require(price > 0) { "상품 가격은 0보다 커야 합니다." }
    }
}
