package com.lucas.instock.data.local.database.pageConfig

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lucas.instock.data.model.PageConfig
import com.lucas.instock.data.model.ProductPageInfo

@Dao
interface PageConfigDao {

    @Query(
        "SELECT * FROM pageConfig " +
                "JOIN productPageConfig ON productPageConfig.pageConfigId = pageConfigId " +
                "WHERE productPageConfig.productId LIKE :productId"
    )
    fun getPageConfigurationByProductId(productId: Int): PageConfig?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pageConfig: PageConfig)

    @Query("DELETE FROM pageConfig")
    suspend fun deleteAll()
}