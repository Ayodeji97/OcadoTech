package com.danzucker.ocadoproducts.business.domain.usecase

import android.util.Log
import com.danzucker.ocadoproducts.business.datasource.remote.model.ProductItemsDto
import com.danzucker.ocadoproducts.business.domain.model.ProductDetails
import com.danzucker.ocadoproducts.business.repository.OcadoProductsRepository
import com.danzucker.ocadoproducts.business.utils.Result
import com.danzucker.ocadoproducts.business.utils.mapper.remoteMapper.ProductsDetailDtoMapper
import javax.inject.Inject

class OcadoProductsDetailUseCase @Inject constructor(
    private val ocadoProductsRepository: OcadoProductsRepository,
    private val productsDetailDtoMapper: ProductsDetailDtoMapper
) {

    suspend operator fun invoke(id : String) : ProductDetails? {
        var ProductDetails : ProductItemsDto? = null
        ocadoProductsRepository.getProductById(id).collect {
            when(it) {
                is Result.Success -> {
                    Log.i("SEEE1999", "${it.data}")
                    Log.i("SEEE", "${id}")
                    ProductDetails = it.data
                }
                is Result.Error -> {
                    Log.i("SEEE4", "Heree")
                    ProductDetails = null
                }
            }
        }

        return (ProductDetails)?.let { productsDetailDtoMapper.transformToEntity(it) }
    }

}