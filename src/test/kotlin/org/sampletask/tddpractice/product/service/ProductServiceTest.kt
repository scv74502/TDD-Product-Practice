package org.sampletask.tddpractice.product.service

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.util.Assert
import kotlin.Int
import kotlin.String

class ProductServiceTest {
    private lateinit var productService: ProductService
    private lateinit var productPort: ProductPort
    private lateinit var productRepository: ProductRepository

    @BeforeEach
    fun setUp() {
        productRepository = ProductRepository()
        productPort = ProductAdapter(productRepository)
        productService = ProductService(productPort)
    }

    @Test
    fun 상품등록() {
        val productName = "상품명"
        val productPrice = 1000
        val productDiscountPolicy = DiscountPolicy.NONE
        val request: AddProductRequest = AddProductRequest(productName, productPrice, productDiscountPolicy)
        productService.addProduct(request)
    }

    class ProductService(
        private val productPort: ProductPort,
    ) {
        fun addProduct(request: AddProductRequest) {
            val product =
                Product(
                    name = request.name,
                    price = request.price,
                    discountPolicy = request.discountPolicy,
                )
            productPort.save(product)
        }
    }

    data class AddProductRequest(
        val name: String,
        val price: Int,
        val discountPolicy: DiscountPolicy,
    ) {
        init {
            Assert.isTrue(name.isNotBlank()) { "상품명은 필수입니다." }
            Assert.isTrue(price > 0) { "상품 가격은 0보다 커야 합니다." }
        }
    }

    class Product(
        val name: String,
        val price: Int,
        val discountPolicy: DiscountPolicy,
    ) {
        var id: Long = 0L
            private set // 외부에서 임의로 수정할 수 없도록 setter를 private으로 설정

        init {
            Assert.isTrue(name.isNotBlank()) { "상품명은 필수입니다." }
            Assert.isTrue(price > 0) { "상품 가격은 0보다 커야 합니다." }
            Assert.notNull(discountPolicy, "할인 정책은 필수입니다.")
        }

        fun assignId(id: Long) {
            this.id = id
        }
    }

    enum class DiscountPolicy {
        NONE,
    }

    interface ProductPort {
        fun save(product: Product)
    }

    class ProductAdapter(
        private val productRepository: ProductRepository,
    ) : ProductPort {
        override fun save(product: Product) {
            productRepository.save(product)
        }
    }

    class ProductRepository {
        private val persistence: MutableMap<Long, Product> = mutableMapOf()
        private var sequence: Long = 0L

        fun save(product: Product) {
            product.assignId(++sequence)
            persistence[product.id] = product
        }
    }
}
