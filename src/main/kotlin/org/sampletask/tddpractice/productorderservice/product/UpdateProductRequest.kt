package org.sampletask.tddpractice.productorderservice.product

data class UpdateProductRequest(
    val name: String,
    val price: Int,
    val discountPolicy: DiscountPolicy
)