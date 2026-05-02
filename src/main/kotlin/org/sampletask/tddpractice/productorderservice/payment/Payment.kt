package org.sampletask.tddpractice.productorderservice.payment

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import org.sampletask.tddpractice.productorderservice.order.Order

@Entity
@Table(name = "payments")
class Payment(
    @OneToOne
    val order: Order,
    val cardNumber: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
        private set

    init {
        require(cardNumber.isNotBlank()) { "카드 번호는 필수입니다." }
    }

    fun getPrice(): Int {
        return order.getTotalPrice()
    }
}
