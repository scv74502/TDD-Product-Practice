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
    private lateinit var productPort: ProductPort
    @Autowired
    private lateinit var productService: ProductService

    @BeforeEach
    fun setUp() {
        val productPort = Mockito.mock(ProductPort::class.java)
        productService = ProductService(productPort)
    }


    @Test
    fun `상품수정`(){
        productService.addProduct(ProductSteps.상품등록요청_생성())
        val productId = 1L
        val request = ProductSteps. `상품수정요청_생성`()

        productService.updateProduct(productId, request)

        val response = productService.getProduct(productId)
        val productResponse: GetProductResponse = response.body ?: throw IllegalStateException("응답 본문이 없습니다")
        assertThat(productResponse.name).isEqualTo("상품 수정")
        assertThat(productResponse.price).isEqualTo(2000)
    }

    //    private class StubProductPort : ProductPort {
//        lateinit var getProduct_will_return: Product
//        override fun save(product: Product) {
//
//        }
//
//        override fun getProduct(productId: Long): Product {
//            return getProduct_will_return
//        }
//    }
}