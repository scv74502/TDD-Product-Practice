package org.sampletask.tddpractice.productorderservice.service

import io.restassured.RestAssured
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.sampletask.tddpractice.ApiTest
import org.sampletask.tddpractice.productorderservice.product.AddProductRequest
import org.sampletask.tddpractice.productorderservice.product.DiscountPolicy
import org.sampletask.tddpractice.productorderservice.product.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

class ProductApiTest : ApiTest() {
    @Autowired
    private lateinit var productService: ProductService

    @Test
    fun 상품등록() {
        val request: AddProductRequest = 상품등록요청_생성()

        // API 요청
        val response =
            `상품등록요청`(request)

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value())
    }

    private fun `상품등록요청`(request: AddProductRequest): ExtractableResponse<Response> =
        RestAssured
            .given()
            .log()
            .all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .`when`()
            .post("/products")
            .then()
            .log()
            .all()
            .extract()

    private fun 상품등록요청_생성(): AddProductRequest {
        val productName = "상품명"
        val productPrice = 1000
        val productDiscountPolicy = DiscountPolicy.NONE
        val request: AddProductRequest = AddProductRequest(productName, productPrice, productDiscountPolicy)
        return request
    }
}
