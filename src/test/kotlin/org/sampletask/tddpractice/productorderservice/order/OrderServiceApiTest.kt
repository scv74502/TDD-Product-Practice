package org.sampletask.tddpractice.productorderservice.order

import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.sampletask.tddpractice.ApiTest
import org.sampletask.tddpractice.productorderservice.product.ProductSteps
import org.springframework.http.HttpStatus


class OrderServiceApiTest : ApiTest() {
    @Test
    fun `상품주문`() {
        ProductSteps.상품등록요청(ProductSteps.상품등록요청_생성())
        val request = OrderSteps.상품주문요청_생성()

        val response: ExtractableResponse<Response> =
            OrderSteps.`상품주문요청`(request)

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value())
    }

}
