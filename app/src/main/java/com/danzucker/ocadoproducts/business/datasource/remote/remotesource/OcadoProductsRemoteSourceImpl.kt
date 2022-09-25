package com.danzucker.ocadoproducts.business.datasource.remote.remotesource

import android.util.Log
import com.danzucker.ocadoproducts.business.datasource.remote.OcadoProductsService
import com.danzucker.ocadoproducts.business.datasource.remote.model.ClustersDto
import com.danzucker.ocadoproducts.business.datasource.remote.model.ProductItemsDto
import com.danzucker.ocadoproducts.business.utils.Result
import com.danzucker.ocadoproducts.di.dispatcher.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.Exception

class OcadoProductsRemoteSourceImpl @Inject constructor(
    private val ocadoProductsService: OcadoProductsService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : OcadoProductsRemoteSource {
    override suspend fun getProducts(): Result<ClustersDto> =
        withContext(ioDispatcher) {
            return@withContext try {
                val apiResponse = ocadoProductsService.getProducts()
                if (apiResponse.isSuccessful) {
                    val productClusters = apiResponse.body()
                    Result.Success(productClusters)
                } else {
                    Result.Success(null)
                }
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    override suspend fun getProductById(id: String): Result<ProductItemsDto> =
        withContext(ioDispatcher) {
            return@withContext try {
                Log.i("SEEE18", "${id}")
                val apiResponse = ocadoProductsService.getProductById(id)
                if (apiResponse.isSuccessful) {
                    Log.i("SEEE11", "${apiResponse.body()}")
                    val productItemsDto = apiResponse.body()
                    Result.Success(productItemsDto)
                } else {
                    Log.i("SEEE12", "${apiResponse.body()}")
                    Result.Success(null)
                }
            } catch (e: Exception) {
                Log.i("SEEE11", "GGGG")
                Result.Error(e)
            }

        }
}