package com.danzucker.ocadoproducts.business.datasource.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.danzucker.ocadoproducts.business.utils.Constants.PRODUCT_TABLE
import com.google.gson.annotations.SerializedName

@Entity(tableName = PRODUCT_TABLE)
data class ProductsItemEntity(
    @ColumnInfo(name = "productId")
    @PrimaryKey
    val id : Int,
    @ColumnInfo(name = "price")
    val price : String,
    @ColumnInfo(name = "title")
    val title : String,
    @ColumnInfo(name = "size")
    val size : String,
    @ColumnInfo(name = "imageUrl")
    val imageUrl : String,
)
