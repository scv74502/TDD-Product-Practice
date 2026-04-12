package org.sampletask.tddpractice.productorderservice.product

import io.restassured.RestAssured
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import org.sampletask.tddpractice.ApiTest
import org.springframework.http.MediaType

open class ProductSteps : ApiTest() {
    companion object {
        fun `상품등록요청`(request: AddProductRequest): ExtractableResponse<Response> =
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

        fun `상품등록요청_생성`(): AddProductRequest {
            val productName = "상품명"
            val productPrice = 1000
            val productDiscountPolicy = DiscountPolicy.NONE
            val request = AddProductRequest(productName, productPrice, productDiscountPolicy)
            return request
        }

        fun `상품수정요청_생성`(): UpdateProductRequest {
            val changedProductName = "상품 수정"
            val changedProductPrice = 2000
            val request = UpdateProductRequest(changedProductName, changedProductPrice, DiscountPolicy.NONE)
            return request
        }
    }
}

