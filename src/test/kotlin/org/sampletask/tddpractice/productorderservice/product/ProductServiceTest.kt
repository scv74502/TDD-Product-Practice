package org.sampletask.tddpractice.productorderservice.product

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
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
        val changedName = "상품 수정"
        val changedPrice = 2000

        val productId = 1L
        val request = UpdateProductRequest(changedName, changedPrice, DiscountPolicy.NONE)

        productService.updateProduct(productId, request)

        val response = productService.getProduct(productId)
        val productResponse: GetProductResponse = response.body ?: throw IllegalStateException("응답 본문이 없습니다")
        assertThat(productResponse.name).isEqualTo(changedName)
        assertThat(productResponse.price).isEqualTo(changedPrice)
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