package com.danzucker.ocadoproducts.business.datasource.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.danzucker.ocadoproducts.business.datasource.cache.model.ProductsItemEntity

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(productItems : List<ProductsItemEntity>)

    @Query("SELECT * FROM PRODUCT_TABLE")
    suspend fun getAllProductItems () : List<ProductsItemEntity>

}