package com.danzucker.ocadoproducts.business.remote.remotesource

import com.danzucker.ocadoproducts.business.datasource.remote.OcadoProductsService
import com.danzucker.ocadoproducts.business.datasource.remote.model.ProductDto
import com.danzucker.ocadoproducts.business.datasource.remote.remotesource.OcadoProductsRemoteSource
import com.danzucker.ocadoproducts.business.datasource.remote.remotesource.OcadoProductsRemoteSourceImpl
import com.danzucker.ocadoproducts.business.repository.OcadoProductsRepositoryTest
import com.danzucker.ocadoproducts.business.utils.MainCoroutineRule
import com.danzucker.ocadoproducts.business.utils.TestConstants.EMPTY_SEARCH_NUMBER
import com.danzucker.ocadoproducts.business.utils.TestConstants.PRODUCTS_JSON_NAME
import com.danzucker.ocadoproducts.business.utils.TestConstants.REQUEST_PATH

import com.google.common.truth.Truth.assertThat
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OcadoProductsRemoteSourceImplTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var ocadoProductService : OcadoProductsService
    private lateinit var ocadoProductsRemoteSource : OcadoProductsRemoteSource
    private lateinit var baseUrl: HttpUrl


    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()



    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        baseUrl = mockWebServer.url("/")
        ocadoProductService = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(OcadoProductsService::class.java)

        ocadoProductsRemoteSource = OcadoProductsRemoteSourceImpl(ocadoProductService, Dispatchers.Main)

    }

    private fun enqueueMockResponse(fileName: String) {
        javaClass.classLoader?.let {
            val inputStream = it.getResourceAsStream(fileName)
            val source = inputStream.source().buffer()
            val mockResponse = MockResponse()
            mockResponse.setBody(source.readString(Charsets.UTF_8))
            mockWebServer.enqueue(mockResponse)
        }
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }


    @Test
    fun `check that searchCharacters return at least a corresponding correct data`() = runBlocking {
        enqueueMockResponse(PRODUCTS_JSON_NAME)
        val productList : List<ProductDto>? =
            ocadoProductService.getProducts().body()?.clusters
        assertThat(productList?.size).isAtLeast(1)
    }

    @Test
    fun `check that searchCharacters returns correct list size`() = runBlocking {
        enqueueMockResponse(PRODUCTS_JSON_NAME)
        val products : List<ProductDto>? = ocadoProductService.getProducts().body()?.clusters
        assertThat(products?.size).isNotEqualTo(EMPTY_SEARCH_NUMBER)
        assertThat(products?.size).isEqualTo(2)
    }

}