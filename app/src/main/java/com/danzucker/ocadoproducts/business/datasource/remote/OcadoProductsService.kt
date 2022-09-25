package com.danzucker.ocadoproducts.business.datasource.remote

import com.danzucker.ocadoproducts.business.datasource.remote.model.ClustersDto
import com.danzucker.ocadoproducts.business.datasource.remote.model.ProductItemsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface OcadoProductsService {
    @GET("products")
    suspend fun getProducts() : Response<ClustersDto>

    @GET("product/{product_id}")
    suspend fun getProductById(
        @Path("product_id") product_id : String
    ) : Response<ProductItemsDto>
}