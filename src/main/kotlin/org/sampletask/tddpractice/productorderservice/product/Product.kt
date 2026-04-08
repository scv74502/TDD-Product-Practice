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
    val name: String,
    price: Int,
    discountPolicy: DiscountPolicy,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L

    var price: Int = price
        protected set

    var discountPolicy: DiscountPolicy = discountPolicy
        protected set

    fun assignId(id: Long) {
        this.id = id
    }
}
