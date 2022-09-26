package com.danzucker.ocadoproducts.presentation.productlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danzucker.ocadoproducts.business.domain.usecase.OcadoProductsUseCase
import com.danzucker.ocadoproducts.business.utils.Constants.NO_PRODUCT_AVAILABLE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val ocadoProductsUseCase: OcadoProductsUseCase,
) : ViewModel() {

    private var _productListViewState = MutableStateFlow(ProductListViewState())
    val productListViewState = _productListViewState.asStateFlow()

    fun onTriggeredEvent (event: ProductListEvent) {
        when(event) {
            ProductListEvent.GetProductList -> {
                getProductList()
            }
        }
    }


    private fun getProductList() {
        viewModelScope.launch {
            _productListViewState.value.let { state ->
                _productListViewState.value = state.copy(isLoading = true)
                val productListItem = ocadoProductsUseCase.invoke()
                if (productListItem.isEmpty()) {
                    _productListViewState.value =
                        state.copy(productList = emptyList(), error = NO_PRODUCT_AVAILABLE)
                } else {
                    _productListViewState.value = state.copy(productList = productListItem)
                }
            }
        }
    }

}