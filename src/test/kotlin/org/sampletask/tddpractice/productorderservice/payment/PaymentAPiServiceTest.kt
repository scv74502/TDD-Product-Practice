package org.sampletask.tddpractice.productorderservice.payment
import io.restassured.RestAssured
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.sampletask.tddpractice.ApiTest
import org.sampletask.tddpractice.productorderservice.order.OrderSteps
import org.sampletask.tddpractice.productorderservice.product.ProductSteps
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

class PaymentApiServiceTest : ApiTest() {
    @Test
    fun `상품주문`() {
        ProductSteps.상품등록요청(ProductSteps.상품등록요청_생성())
        OrderSteps.상품주문요청(OrderSteps.상품주문요청_생성())
        val request = PaymentSteps.주문결제요청_생성()

        val response =
            RestAssured
                .given()
                .log()
                .all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .`when`()
                .post("/payments")
                .then()
                .log()
                .all()
                .extract()

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value())
    }
}
