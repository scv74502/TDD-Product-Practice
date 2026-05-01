package org.sampletask.tddpractice.productorderservice.payment

import org.springframework.stereotype.Component

@Component
class ConsolePaymentGateway : PaymentGateway {
    override fun execute(totalPrice: Int, cardNumber: String) {
        println("$cardNumber 카드로 $totalPrice 결제 완료")
    }
}
