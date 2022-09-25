package com.danzucker.ocadoproducts.presentation.productdetail

import com.danzucker.ocadoproducts.business.domain.model.ProductDetails

data class ProductDetailViewState(
    val isLoading : Boolean = false,
    val productDetail : ProductDetails? = null,
    val error : String = ""
)
