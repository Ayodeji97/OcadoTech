package com.danzucker.ocadoproducts.business.datasource.remote.remotesource

import com.danzucker.ocadoproducts.business.datasource.remote.model.ClustersDto
import com.danzucker.ocadoproducts.business.datasource.remote.model.ProductItemsDto
import com.danzucker.ocadoproducts.business.utils.Result

interface OcadoProductsRemoteSource {
    suspend fun getProducts () : Result<ClustersDto>
    suspend fun getProductById (id : String) : Result<ProductItemsDto>
}