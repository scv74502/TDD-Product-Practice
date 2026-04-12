package org.sampletask.tddpractice.productorderservice.product

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class ProductServiceTest(

) {
    private lateinit var productService: ProductService

    @BeforeEach
    fun setUp() {
        val productPort = Mockito.mock(ProductPort::class.java)
        productService = ProductService(productPort)
    }


    @Test
    fun `상품수정`(){
        val changedName = "상품 수정"
        val changedPrice = 2000

        val productId = 1L
        val request: UpdateProductRequest = UpdateProductRequest(changedName, changedPrice, DiscountPolicy.NONE)
        val product = Product("상품명", 1000, DiscountPolicy.NONE)

        productPort.getProduct_will_return = product

        productService.updateProduct(productId, request)

        assertThat(product.name).isEqualTo(changedName)
        assertThat(product.price).isEqualTo(changedPrice)
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