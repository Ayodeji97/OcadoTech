package com.danzucker.ocadoproducts.business.cache.cachesource

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.danzucker.ocadoproducts.business.datasource.cache.OcadoProductsDatabase
import com.danzucker.ocadoproducts.business.datasource.cache.dao.ProductDao
import com.danzucker.ocadoproducts.business.utils.DummyData
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class OcadoProductsCacheSourceTest {

    private lateinit var ocadoProductsDb : OcadoProductsDatabase
    private val context: Context = ApplicationProvider.getApplicationContext()
    private lateinit var ocadoProductsDao : ProductDao

    @Before
    fun initDb () {
        ocadoProductsDb = Room.inMemoryDatabaseBuilder(
            context,
            OcadoProductsDatabase::class.java
        ).allowMainThreadQueries().build()

        ocadoProductsDao = ocadoProductsDb.productDao()
    }

    @Test
    fun `is product saved on db` () = runBlocking {
        ocadoProductsDao.insertAll(listOf(DummyData.productsItemEntity, DummyData.productsItemEntity2))
        val productList = ocadoProductsDao.getAllProductItems()
        assertThat(productList).isNotEmpty()
        assertThat(productList.size).isGreaterThan(0)
    }

    @Test
    fun `is product saved on db correctly saved in db` () = runBlocking {
        ocadoProductsDao.insertAll(listOf(DummyData.productsItemEntity, DummyData.productsItemEntity2))
        val productList = ocadoProductsDao.getAllProductItems()
        assertThat(productList).isNotEmpty()
        assertThat(productList.first()).isNotEqualTo(DummyData.productsItemEntity)
        assertThat(productList.last()).isNotEqualTo(DummyData.productsItemEntity2)
    }


    @After
    fun tearDb () {
        ocadoProductsDb.close()
    }
}