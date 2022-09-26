package com.danzucker.ocadoproducts.di.cachesource

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.danzucker.ocadoproducts.business.datasource.cache.OcadoProductsDatabase
import com.danzucker.ocadoproducts.business.datasource.cache.dao.ProductDao
import com.danzucker.ocadoproducts.business.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheSourceModule {

    @Singleton
    @Provides
    fun provideAppDb(app: Application): OcadoProductsDatabase =
         Room
            .databaseBuilder(app, OcadoProductsDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration() // get correct db version if schema changed
            .build()


    @Singleton
    @Provides
    fun provideProductDao (ocadoProductsDatabase: OcadoProductsDatabase) : ProductDao =
        ocadoProductsDatabase.productDao()
}