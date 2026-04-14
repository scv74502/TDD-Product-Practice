package org.sampletask.tddpractice.productorderservice.order

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import org.sampletask.tddpractice.productorderservice.product.Product

@Entity
@Table(name = "orders")
class Order(
    @OneToOne
    val product: Product,
    val quantity: Int
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    init {
        require(quantity > 0) { "수량은 0보다 커야 합니다." }
    }
}
