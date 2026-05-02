package org.sampletask.tddpractice.productorderservice.product.application.service

import org.sampletask.tddpractice.productorderservice.product.domain.DiscountPolicy

data class UpdateProductRequest(
    val name: String,
    val price: Int,
    val discountPolicy: DiscountPolicy,
)
