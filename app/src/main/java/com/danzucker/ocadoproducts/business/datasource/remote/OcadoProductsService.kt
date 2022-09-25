package com.danzucker.ocadoproducts.business.datasource.remote

import com.danzucker.ocadoproducts.business.datasource.remote.model.ClustersDto
import retrofit2.Response
import retrofit2.http.GET

interface OcadoProductsService {
    @GET("products")
    suspend fun getProducts() : Response<ClustersDto>
}