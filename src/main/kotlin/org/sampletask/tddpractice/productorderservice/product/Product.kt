package org.sampletask.tddpractice.productorderservice.product

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.util.Assert

@Entity
@Table(name = "products")
class Product(
    name: String,
    price: Int,
    discountPolicy: DiscountPolicy,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L

    var price: Int = price
        protected set

    var name: String = name
        protected set

    var discountPolicy: DiscountPolicy = discountPolicy
        protected set

    fun assignId(id: Long) {
        this.id = id
    }

    fun update(
        name: String,
        price: Int,
        discountPolicy: DiscountPolicy,
    ) {
        Assert.isTrue(name.isNotBlank()) { "상품명은 필수입니다." }
        Assert.isTrue(price > 0) { "상품 가격은 0보다 커야 합니다." }
        this.name = name
        this.price = price
        this.discountPolicy = discountPolicy
    }
}
