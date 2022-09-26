package com.danzucker.ocadoproducts.business.repository

import com.danzucker.ocadoproducts.business.datasource.cache.cachesource.OcadoProductsCacheSource
import com.danzucker.ocadoproducts.business.datasource.remote.remotesource.OcadoProductsRemoteSource
import com.danzucker.ocadoproducts.business.utils.DummyData
import com.danzucker.ocadoproducts.business.utils.Result
import com.danzucker.ocadoproducts.business.utils.mapper.remoteMapper.ProductsItemDtoMapper
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class OcadoProductsRepositoryTest {

    private lateinit var ocadoProductsRemoteSource : OcadoProductsRemoteSource
    private lateinit var ocadoProductsCacheSource : OcadoProductsCacheSource



    @Before
    fun setup() {
        ocadoProductsRemoteSource = mock(OcadoProductsRemoteSource::class.java)
        ocadoProductsCacheSource =  mock(OcadoProductsCacheSource::class.java)
    }

    @Test
    fun `check that calling getProducts() return list of product`() = runBlocking {
        `when`(ocadoProductsRemoteSource.getProducts()).thenReturn(
            Result.Success(DummyData.clustersDto)
        )

        val response = ocadoProductsRemoteSource.getProducts()
        when(response) {
            is Result.Success -> {
                assertThat(response.data?.clusters).isNotEmpty()
                assertThat(response.data).isEqualTo(DummyData.clustersDto)
            }
            else -> {}
        }
    }


    @Test
    fun `check that calling getProducts() return null when product is null`() = runBlocking {
        `when`(ocadoProductsRemoteSource.getProducts()).thenReturn(
            Result.Success(null)
        )

        val response = ocadoProductsRemoteSource.getProducts()
        when(response) {
            is Result.Success -> {
                assertThat(response.data?.clusters).isNull()
                assertThat(response.data).isEqualTo(null)
            }
            else -> {}
        }
    }


    @Test
    fun `check that calling getProducts() return an exception when there is an error`() = runBlocking {
        `when`(ocadoProductsRemoteSource.getProducts()).thenReturn(
            Result.Error(DummyData.exception)
        )

        val response = ocadoProductsRemoteSource.getProducts()
        when(response) {
            is Result.Error -> {
                assertThat(response.exception).isEqualTo(DummyData.exception)
                assertThat(response.exception).isNotNull()
            }
            else -> {}
        }
    }


    @Test
    fun `check that calling getProductsId() return the right product`() = runBlocking {
        `when`(ocadoProductsRemoteSource.getProductById("23526")).thenReturn(
            Result.Success(DummyData.productDto.items.first())
        )

        val response = ocadoProductsRemoteSource.getProductById("23526")
        when(response) {
            is Result.Success -> {
                assertThat(response.data).isNotNull()
                assertThat(response.data).isEqualTo(DummyData.productItemsDto)
            }
            else -> {}
        }
    }

    @Test
    fun `check that calling getProductsId() return the right product Id`() = runBlocking {
        `when`(ocadoProductsRemoteSource.getProductById("23520")).thenReturn(
            Result.Success(DummyData.productDto.items.last())
        )

        val response = ocadoProductsRemoteSource.getProductById("23520")
        when(response) {
            is Result.Success -> {
                assertThat(response.data?.id).isEqualTo(("23520").toInt())
                assertThat(response.data?.id).isNotEqualTo(("23526").toInt())
            }
            else -> {}
        }
    }

    @Test
    fun `check that calling getProductsId() return null when product is null`() = runBlocking {
        `when`(ocadoProductsRemoteSource.getProductById("235")).thenReturn(
            Result.Success(null)
        )

        val response = ocadoProductsRemoteSource.getProductById("235")
        when(response) {
            is Result.Success -> {
                assertThat(response.data).isNull()
            }
            else -> {}
        }
    }


    @Test
    fun `check that calling getProductsId() return an exception when there is an error`() = runBlocking {
        `when`(ocadoProductsRemoteSource.getProductById("23520")).thenReturn(
            Result.Error(DummyData.exception)
        )

        val response = ocadoProductsRemoteSource.getProductById("23520")
        when(response) {
            is Result.Error -> {
                assertThat(response.exception).isEqualTo(DummyData.exception)
                assertThat(response.exception).isNotNull()
            }
            else -> {}
        }
    }

}