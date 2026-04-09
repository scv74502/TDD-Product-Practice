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

    @Test
    fun `상품조회`() {
        ProductSteps.상품등록요청(ProductSteps.상품등록요청_생성())
        val productId: Long = 1L

        val response: ExtractableResponse<Response> =
            RestAssured
                .given()
                .log()
                .all()
                .`when`()
                .get("/products/{productId}", productId)
                .then()
                .log()
                .all()
                .extract()

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value())
        assertThat(response.jsonPath().getString("name")).isEqualTo("상품명")
    }
}
