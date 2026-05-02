package org.sampletask.tddpractice.productorderservice.payment.adatper

import org.sampletask.tddpractice.productorderservice.payment.domain.Payment
import org.springframework.data.jpa.repository.JpaRepository

interface PaymentRepository : JpaRepository<Payment, Long> {
    fun save(payment: Payment)
}
