package org.sampletask.tddpractice.productorderservice.order

import org.sampletask.tddpractice.productorderservice.product.Product

class OrderService(
    val orderPort: OrderPort
) {
    fun createOrder(request: CreateOrderRequest){
        val product: Product = orderPort.getProductById(request.productId)
        val order: Order = Order(product, request.quantity)
        orderPort.save(order)
    }
}