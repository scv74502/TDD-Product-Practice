package org.sampletask.tddpractice.productorderservice.product

import io.restassured.RestAssured
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus

class ProductApiTest : ProductSteps() {
    @Autowired
    private lateinit var productService: ProductService

    @Test
    fun `상품등록`() {
        val request: AddProductRequest = 상품등록요청_생성()

        // API 요청
        val response =
            `상품등록요청`(request)

        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value())
    }

    @Test
    fun `상품조회`() {
        상품등록요청(상품등록요청_생성())
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

        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value())
        Assertions.assertThat(response.jsonPath().getString("name")).isEqualTo("상품명")
    }
}