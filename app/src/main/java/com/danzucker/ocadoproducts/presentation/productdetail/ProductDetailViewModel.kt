package com.danzucker.ocadoproducts.presentation.productdetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danzucker.ocadoproducts.business.domain.usecase.OcadoProductsDetailUseCase
import com.danzucker.ocadoproducts.presentation.productlist.ProductListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val ocadoProductsDetailUseCase: OcadoProductsDetailUseCase
) : ViewModel() {

    private var _productListViewState = MutableStateFlow(ProductDetailViewState())
    val productDetailViewState = _productListViewState.asStateFlow()


    fun onTriggeredEvent (event : ProductDetailsEvent) {
        when(event) {
            is ProductDetailsEvent.ProductDetails -> {
                getProductById(event.id)
            }
        }
    }

    private fun getProductById (id : String) {
        viewModelScope.launch {
            _productListViewState.value.let { state->
                _productListViewState.value = state.copy(isLoading = true)
                val productDetail = ocadoProductsDetailUseCase.invoke(id)
                if (productDetail != null) {
                    Log.i("SEEE2", "${productDetail}")
                    _productListViewState.value = state.copy(productDetail = productDetail)
                } else {
                    Log.i("SEEE1", "${productDetail}")
                    _productListViewState.value = state.copy(error = "Something went wrong!")
                }
            }
        }
    }
}