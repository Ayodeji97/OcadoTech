package com.danzucker.ocadoproducts.presentation.productlist

sealed class ProductListEvent {
    object GetProductList : ProductListEvent()
}
