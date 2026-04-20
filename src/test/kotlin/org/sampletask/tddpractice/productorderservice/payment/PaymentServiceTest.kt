package org.sampletask.tddpractice.productorderservice.payment

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.sampletask.tddpractice.productorderservice.order.Order
import org.sampletask.tddpractice.productorderservice.product.DiscountPolicy
import org.sampletask.tddpractice.productorderservice.product.Product
import kotlin.text.isBlank

class PaymentServiceTest {
    lateinit var paymentService: PaymentService

    @BeforeEach
    fun setUp() {
        val paymentGateway = ConsolePaymentGateway()
        val paymentRepository = PaymentRepository()
        val paymentPort = PaymentAdapter(paymentGateway, paymentRepository)
        paymentService = PaymentService(paymentPort)
    }

    @Test
    fun `상품주문`() {
        val orderId = 1L
        val cardNumber = "1234-1234-1234-1234"
        val request = PaymentRequest(orderId, cardNumber)
        paymentService.payment(request)
    }

    data class PaymentRequest(
        val orderId: Long,
        val cardNumber: String,
    ) {
        init {
            require(!cardNumber.isBlank())
        }
    }

    class PaymentService(
        val paymentPort: PaymentPort,
    ) {
        fun payment(request: PaymentRequest) {
            val order = paymentPort.getOrder(request.orderId)
            val payment: Payment = Payment(order, request.cardNumber)
        }
    }

    class Payment(
        val order: Order,
        val cardNumber: String,
    ) {
        var id: Long = 0
            private set

        init {
            require(!cardNumber.isBlank())
        }

        fun assignId(id: Long) {
            this.id = id
        }
    }

    class PaymentAdapter(
        val paymentGateway: PaymentGateway,
        val paymentRepository: PaymentRepository,
    ) : PaymentPort {
        override fun getOrder(orderId: Long): Order = Order(Product("상품1", 1000, DiscountPolicy.NONE), 2)

        override fun pay(payment: Payment) {
            paymentGateway.execute(payment)
        }

        override fun save(payment: Payment) {
            paymentRepository.save(payment)
        }
    }

    class PaymentRepository {
        var persistence: MutableMap<Long, Payment> = mutableMapOf()
        var sequence = 0L

        fun save(payment: Payment) {
            payment.assignId(++sequence)
            persistence[payment.id] = payment
        }
    }

    class ConsolePaymentGateway : PaymentGateway {
        override fun execute(payment: Payment) {
            println("${payment.cardNumber} 카드로 ${payment.order} 결제 완료")
        }
    }

    interface PaymentPort {
        fun getOrder(orderId: Long): Order

        fun pay(payment: Payment)

        fun save(payment: Payment)
    }

    interface PaymentGateway {
        fun execute(payment: Payment)
    }
}
