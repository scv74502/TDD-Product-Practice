package org.sampletask.tddpractice.productorderservice.order.adapter

import org.sampletask.tddpractice.productorderservice.order.domain.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, Long>
