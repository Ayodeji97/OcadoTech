package com.danzucker.ocadoproducts.di.remotesource

import com.danzucker.ocadoproducts.business.datasource.remote.remotesource.OcadoProductsRemoteSource
import com.danzucker.ocadoproducts.business.datasource.remote.remotesource.OcadoProductsRemoteSourceImpl
import com.danzucker.ocadoproducts.business.repository.OcadoProductsRepository
import com.danzucker.ocadoproducts.business.repository.OcadoProductsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteSourceModule {
    @Binds
    abstract fun provideOcadoRemoteSource (
        ocadoProductsRemoteSourceImpl: OcadoProductsRemoteSourceImpl
    ) : OcadoProductsRemoteSource
}