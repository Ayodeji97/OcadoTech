package com.danzucker.ocadoproducts.di.repository

import com.danzucker.ocadoproducts.business.repository.OcadoProductsRepository
import com.danzucker.ocadoproducts.business.repository.OcadoProductsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class OcadoRepositoryModule {
    @Binds
    abstract fun provideOcadoRepository (
        ocadoProductsRepositoryImpl: OcadoProductsRepositoryImpl
    ) : OcadoProductsRepository
}