package org.sampletask.tddpractice.productorderservice.payment

import org.springframework.data.jpa.repository.JpaRepository

interface PaymentRepository : JpaRepository<Payment, Long> {
    fun save(payment: Payment)
}
