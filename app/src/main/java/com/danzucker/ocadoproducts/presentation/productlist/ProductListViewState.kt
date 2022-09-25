package com.danzucker.ocadoproducts.presentation.productlist

import com.danzucker.ocadoproducts.business.domain.model.ProductItems


data class ProductListViewState(
    val isLoading: Boolean = false,
    val productList: List<ProductItems> = listOf(),
    val error: String = ""
)
