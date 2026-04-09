package org.sampletask.tddpractice.productorderservice.service

import io.restassured.RestAssured
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.sampletask.tddpractice.productorderservice.product.AddProductRequest
import org.sampletask.tddpractice.productorderservice.product.DiscountPolicy
import org.sampletask.tddpractice.productorderservice.product.ProductService
import org.sampletask.tddpractice.productorderservice.product.ProductSteps
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

class ProductApiTest : ProductSteps() {
    @Autowired
    private lateinit var productService: ProductService

    @Test
    fun `상품등록`() {
        val request: AddProductRequest = 상품등록요청_생성()

        // API 요청
        val response =
            `상품등록요청`(request)

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value())
    }
}
