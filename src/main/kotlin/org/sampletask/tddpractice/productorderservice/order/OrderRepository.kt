package org.sampletask.tddpractice.productorderservice.order

class OrderRepository(
    val persistence: MutableMap<Long, Order> = mutableMapOf(),
    private var sequence: Long = 0L
) {
    fun save(order:Order){
        persistence.put(++sequence, order)
        order.assignId(sequence)
    }
}