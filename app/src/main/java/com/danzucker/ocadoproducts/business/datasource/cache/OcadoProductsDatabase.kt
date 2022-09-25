package com.danzucker.ocadoproducts.business.datasource.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.danzucker.ocadoproducts.business.datasource.cache.dao.ProductDao
import com.danzucker.ocadoproducts.business.datasource.cache.model.ProductsItemEntity

@Database(
    entities = [ProductsItemEntity::class],
    version = 1,
    exportSchema = false
)
abstract class OcadoProductsDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
}