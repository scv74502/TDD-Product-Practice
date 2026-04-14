package org.sampletask.tddpractice.productorderservice.order

import org.springframework.stereotype.Repository

@Repository
class OrderRepository(
    val persistence: MutableMap<Long, Order> = mutableMapOf(),
    private var sequence: Long = 0L
) {
    fun save(order:Order){
        persistence.put(++sequence, order)
        order.assignId(sequence)
    }
}