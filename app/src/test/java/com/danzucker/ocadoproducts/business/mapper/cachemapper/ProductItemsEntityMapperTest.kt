package com.danzucker.ocadoproducts.business.mapper.cachemapper

import com.danzucker.ocadoproducts.business.utils.DummyData
import com.danzucker.ocadoproducts.business.utils.mapper.cacheMapper.ProductItemsEntityMapper
import com.danzucker.ocadoproducts.business.utils.mapper.remoteMapper.ProductsDetailDtoMapper
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ProductItemsEntityMapperTest {

    private lateinit var productItemsEntityMapper: ProductItemsEntityMapper

    @Before
    fun setup () {
        productItemsEntityMapper = ProductItemsEntityMapper()
    }

    @Test
    fun `map local data against domain data returns data are correctly mapped` () = runTest {
        val productItemDetailEntity = DummyData.productsItemEntity
        val mapperToProductDetail = productItemsEntityMapper.transformToDomain(listOf(productItemDetailEntity))
        assertThat(productItemDetailEntity.id).isEqualTo(mapperToProductDetail.first().id)
    }

    @Test
    fun `map local data against domain data returns data are incorrectly mapped` () = runTest {
        val productItemDetailEntity = DummyData.productsItemEntity
        val mapperToProductDetail = productItemsEntityMapper.transformToDomain(listOf(productItemDetailEntity))
        assertThat(DummyData.productsItemEntity2).isNotEqualTo(mapperToProductDetail.first().id)
    }
}