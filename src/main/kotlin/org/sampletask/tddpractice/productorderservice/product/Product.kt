package org.sampletask.tddpractice.productorderservice.product

import org.springframework.util.Assert

class Product(
    val name: String,
    val price: Int,
    val discountPolicy: DiscountPolicy,
) {
    var id: Long = 0L
        private set // 외부에서 임의로 수정할 수 없도록 setter를 private으로 설정

    init {
        Assert.isTrue(name.isNotBlank()) { "상품명은 필수입니다." }
        Assert.isTrue(price > 0) { "상품 가격은 0보다 커야 합니다." }
        Assert.notNull(discountPolicy, "할인 정책은 필수입니다.")
    }

    fun assignId(id: Long) {
        this.id = id
    }
}