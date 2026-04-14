package org.sampletask.tddpractice.productorderservice.order

import io.restassured.RestAssured
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.sampletask.tddpractice.ApiTest
import org.sampletask.tddpractice.productorderservice.product.ProductSteps
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType


class OrderServiceApiTest : ApiTest() {
    @Test
    fun `상품주문`() {
        ProductSteps.상품등록요청(ProductSteps.상품등록요청_생성())
        val request = 상품주문요청_생성()

        val response: ExtractableResponse<Response> =
            RestAssured
                .given()
                .log()
                .all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .`when`()
                .post("/orders")
                .then()
                .log()
                .all()
                .extract()

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value())
    }

    private fun `상품주문요청_생성`(): CreateOrderRequest {
        val productId: Long = 1L
        val quantity: Int = 1
        val orderRequest: CreateOrderRequest = CreateOrderRequest(productId, quantity)
        return orderRequest
    }
}
