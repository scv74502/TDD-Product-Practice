package org.sampletask.tddpractice.productorderservice.product

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products")
class ProductService(
    private val productPort: ProductPort,
) {
    @PostMapping
    @Transactional
    fun addProduct(
        @RequestBody request: AddProductRequest,
    ): ResponseEntity<Void> {
        val product =
            Product(
                name = request.name,
                price = request.price,
                discountPolicy = request.discountPolicy,
            )
        productPort.save(product)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    fun getProduct(productId: Long): GetProductResponse {
        val product = productPort.getProduct(productId)
        return GetProductResponse(
            product.id,
            product.name,
            product.price,
            product.discountPolicy,
        )
    }
}
