package com.danzucker.ocadoproducts.business.mapper.remotemapper

import androidx.test.filters.SmallTest
import com.danzucker.ocadoproducts.business.utils.DummyData
import com.danzucker.ocadoproducts.business.utils.mapper.remoteMapper.ProductsItemDtoMapper
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
@SmallTest
class ProductsItemDtoMapperTest {

    private lateinit var productsItemDtoMapper: ProductsItemDtoMapper

    @Before
    fun setup () {
        productsItemDtoMapper = ProductsItemDtoMapper()
    }

    @Test
    fun `map remote data against local data returns data are correctly mapped` () = runTest {
        val productItemDto = DummyData.productItemsDto
        val mapperToProductEntity = productsItemDtoMapper.transformToEntity(listOf(productItemDto))
        assertThat(productItemDto.id).isEqualTo(mapperToProductEntity.first().id)
    }

    @Test
    fun `map remote entity data against domain data return data are not correctly mapped` () = runTest {
        val productItemDto = DummyData.productItemsDto
        val mapperToProductEntity = productsItemDtoMapper.transformToEntity(listOf(productItemDto))
        assertThat(DummyData.productItemsDto2.id).isNotEqualTo(mapperToProductEntity.first().id)
    }




}