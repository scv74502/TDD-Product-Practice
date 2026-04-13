package org.sampletask.tddpractice.productorderservice.order

import org.aspectj.weaver.ast.Or
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.sampletask.tddpractice.productorderservice.product.DiscountPolicy
import org.sampletask.tddpractice.productorderservice.product.Product
import org.sampletask.tddpractice.productorderservice.product.ProductRepository
import org.springframework.data.repository.findByIdOrNull

class OrderServiceTest {
    private lateinit var orderService: OrderService
    private lateinit var orderPort: OrderPort

    @BeforeEach
    fun setUp() {
        val orderRepository = OrderRepository()
        orderPort = object : OrderPort {
          override fun getProductById(productId: Long): Product {
              return Product("상품명", 1000, DiscountPolicy.NONE)
          }

          override fun save(order: Order) {
              orderRepository.save(order)
          }
      }
        orderService = OrderService(orderPort)
    }

    @Test
    fun `상품주문`() {
        val productId: Long = 1L
        val quantity: Int = 1
        val request : CreateOrderRequest = CreateOrderRequest(productId, quantity)
        orderService.createOrder(request);
    }

    data class CreateOrderRequest(
        val productId: Long,
        val quantity: Int
    ) {
        init {
            require(quantity > 0) { "수량은 0보다 커야 합니다." }
        }
    }

    class Order(
        val product: Product,
        val quantity: Int
    ) {
        private var _orderId: Long = 0L

        val orderId: Long
            get() = _orderId


        init {
            require(quantity > 0) { "수량은 0보다 커야 합니다." }
        }

        fun assignId(orderId: Long){
            this._orderId = orderId
        }
    }

    class OrderService(
        val orderPort: OrderPort
    ) {
        fun createOrder(request: CreateOrderRequest){
            val product: Product = orderPort.getProductById(request.productId)
            val order: Order = Order(product, request.quantity)
            orderPort.save(order)
        }
    }

    class OrderAdapter(
        val productRepository: ProductRepository,
        val orderRepository: OrderRepository
    ):OrderPort {
        override fun getProductById(productId: Long): Product {
            return productRepository.findByIdOrNull(productId)
                ?: throw NoSuchElementException("상품이 존재하지 않습니다")
        }

        override fun save(order: Order) {
            orderRepository.save(order)
        }
    }

    interface OrderPort{
        fun getProductById(productId: Long): Product
        fun save(order: Order)
    }

    class OrderRepository(
        val persistence: MutableMap<Long, Order> = mutableMapOf(),
        private var sequence: Long = 0L
    ) {
        fun save(order:Order){
            persistence.put(++sequence, order)
            order.assignId(sequence)
        }
    }
}


