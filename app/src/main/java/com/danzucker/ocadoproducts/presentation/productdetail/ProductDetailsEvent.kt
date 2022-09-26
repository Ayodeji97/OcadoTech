package com.danzucker.ocadoproducts.presentation.productdetail

sealed class ProductDetailsEvent {
    data class ProductDetails(
        val id: String
    ) : ProductDetailsEvent()
}
