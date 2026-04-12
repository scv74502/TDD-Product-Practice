package org.sampletask.tddpractice.productorderservice.product

import io.restassured.RestAssured
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

class ProductApiTestProductApiTest : ProductSteps() {
    @Autowired
    private lateinit var productService: ProductService

    @Autowired
    private lateinit var productRepository: ProductRepository

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
        상품등록요청((상품등록요청_생성()))
        val productId: Long = 1L
        val response: ExtractableResponse<Response> =
            `상품조회요청`(productId)

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value())
        assertThat(response.jsonPath().getString("name")).isEqualTo("상품명")
    }

    public fun `상품조회요청`(productId: Long): ExtractableResponse<Response> =
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

    @Test
    fun `상품수정`() {
        상품등록요청((상품등록요청_생성()))
        val productId = 1L
        val request = 상품수정요청_생성()
        val response: ExtractableResponse<Response> =
            RestAssured
                .given()
                .log()
                .all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .`when`()
                .patch("/products/{productId}", productId)
                .then()
                .log()
                .all()
                .extract()
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value())
        assertThat(productRepository.findById(1L).get().name).isEqualTo("상품 수정")
    }
}
