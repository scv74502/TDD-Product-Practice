package org.sampletask.tddpractice.productorderservice.order

import io.restassured.RestAssured
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import org.springframework.http.MediaType

class OrderSteps {
    companion object {
        fun `상품주문요청_생성`(): CreateOrderRequest {
            val productId: Long = 1L
            val quantity: Int = 1
            val orderRequest: CreateOrderRequest = CreateOrderRequest(productId, quantity)
            return orderRequest
        }

        fun `상품주문요청`(request: CreateOrderRequest): ExtractableResponse<Response> =
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
    }
}
