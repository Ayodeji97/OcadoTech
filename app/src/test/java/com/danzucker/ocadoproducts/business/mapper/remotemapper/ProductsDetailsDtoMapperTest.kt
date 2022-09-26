package com.danzucker.ocadoproducts.business.mapper.remotemapper

import com.danzucker.ocadoproducts.business.utils.DummyData
import com.danzucker.ocadoproducts.business.utils.mapper.remoteMapper.ProductsDetailDtoMapper
import com.danzucker.ocadoproducts.business.utils.mapper.remoteMapper.ProductsItemDtoMapper
import com.google.common.truth.Truth
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ProductsDetailsDtoMapperTest {

    private lateinit var productsDetailsMapper : ProductsDetailDtoMapper

    @Before
    fun setup () {
        productsDetailsMapper = ProductsDetailDtoMapper()
    }

    @Test
    fun `map remote data against local data returns data are correctly mapped` () = runTest {
        val productItemDetailDto = DummyData.productItemsDto
        val mapperToProductDetail = productsDetailsMapper.transformToEntity(productItemDetailDto)
        Truth.assertThat(productItemDetailDto.id).isEqualTo(mapperToProductDetail.id)
    }

    @Test
    fun `map remote data against local data returns data are invalid mapped` () = runTest {
        val productItemDetailDto = DummyData.productItemsDto
        val mapperToProductDetail = productsDetailsMapper.transformToEntity(productItemDetailDto)
        Truth.assertThat(DummyData.productItemsDto2.id).isNotEqualTo(mapperToProductDetail.id)
    }
}