package com.danzucker.ocadoproducts.business.datasource.cache.cachesource

import com.danzucker.ocadoproducts.business.datasource.cache.dao.ProductDao
import com.danzucker.ocadoproducts.business.datasource.cache.model.ProductsItemEntity
import javax.inject.Inject

class OcadoProductsCacheSource @Inject constructor(
    private val productDao: ProductDao
){
    suspend fun saveProductItems (productItems : List<ProductsItemEntity>) {
        productDao.insertAll(productItems)
    }

    suspend fun getAllProductItems () : List<ProductsItemEntity> =
        productDao.getAllProductItems()

}