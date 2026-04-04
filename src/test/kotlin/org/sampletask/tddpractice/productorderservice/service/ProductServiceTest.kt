package org.sampletask.tddpractice.productorderservice.service

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.sampletask.tddpractice.productorderservice.product.AddProductRequest
import org.sampletask.tddpractice.productorderservice.product.DiscountPolicy
import org.sampletask.tddpractice.productorderservice.product.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ProductServiceTest {
    @Autowired
    private lateinit var productService: ProductService
    

    @Test
    fun 상품등록() {
        val request: AddProductRequest = 상품등록요청_생성()
        productService.addProduct(request)
    }

    private fun 상품등록요청_생성(): AddProductRequest {
        val productName = "상품명"
        val productPrice = 1000
        val productDiscountPolicy = DiscountPolicy.NONE
        val request: AddProductRequest = AddProductRequest(productName, productPrice, productDiscountPolicy)
        return request
    }

}
