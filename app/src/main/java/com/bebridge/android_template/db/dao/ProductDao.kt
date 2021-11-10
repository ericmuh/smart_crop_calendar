package com.bebridge.android_template.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.bebridge.android_template.db.entity.ProductCategoryEntity
import com.bebridge.android_template.db.entity.ProductEntity
import com.bebridge.android_template.db.entity.ProductProductTagEntity
import com.bebridge.android_template.db.entity.ProductTagEntity
import io.reactivex.Completable

@Dao
interface ProductDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertAll(vararg products: ProductEntity)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertAll(products: List<ProductEntity>)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertCategory(productCategoryEntity: ProductCategoryEntity): Completable

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertAllCategories(productCategoryEntity: List<ProductCategoryEntity>)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertAllTags(productTagEntities: List<ProductTagEntity>)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertCategoryAndProduct(productCategoryEntity: ProductCategoryEntity, product: ProductEntity)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertProductFull(
    product: ProductEntity,
    productCategoryEntity: ProductCategoryEntity,
    productTagEntities: List<ProductTagEntity>,
    productProductTagEntity: List<ProductProductTagEntity>
  )

  @Delete
  fun delete(product: ProductEntity)

}