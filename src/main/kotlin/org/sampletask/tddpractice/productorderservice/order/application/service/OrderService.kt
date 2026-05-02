package org.sampletask.tddpractice.productorderservice.order.application.service

import org.sampletask.tddpractice.productorderservice.order.application.port.OrderPort
import org.sampletask.tddpractice.productorderservice.order.domain.Order
import org.sampletask.tddpractice.productorderservice.product.domain.Product
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/orders")
class OrderService(
    val orderPort: OrderPort,
) {
    @PostMapping
    @Transactional
    fun createOrder(
        @RequestBody request: CreateOrderRequest,
    ): ResponseEntity<Void> {
        val product: Product = orderPort.getProductById(request.productId)
        val order: Order = Order(product, request.quantity)
        orderPort.save(order)

        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}
