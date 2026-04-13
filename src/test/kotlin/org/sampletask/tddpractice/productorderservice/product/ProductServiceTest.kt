package org.sampletask.tddpractice.productorderservice.product

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ProductServiceTest {
    @Autowired
    private lateinit var productService: ProductService

    @Test
    fun `상품수정`() {
        val product = productService.addProduct(ProductSteps.상품등록요청_생성())
        val productId = 1L
        val request = ProductSteps.`상품수정요청_생성`()

        productService.updateProduct(productId, request)

        val response = productService.getProduct(productId)
        val productResponse: GetProductResponse = response.body ?: throw IllegalStateException("응답 본문이 없습니다")
        assertThat(productResponse.name).isEqualTo("상품 수정")
        assertThat(productResponse.price).isEqualTo(2000)
    }
}
