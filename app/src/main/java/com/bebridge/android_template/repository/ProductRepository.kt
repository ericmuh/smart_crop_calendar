package com.bebridge.android_template.repository

import com.bebridge.android_template.db.AppDatabase
import com.bebridge.android_template.api.ApiService.IApiService
import com.bebridge.android_template.api.response.PageInfo
import com.bebridge.android_template.api.toProductWithCategoryAndTags
import com.bebridge.android_template.db.model.CategoryWithProducts
import com.bebridge.android_template.db.model.ProductAndCategory
import com.bebridge.android_template.db.model.ProductWithProductTags
import com.bebridge.android_template.db.entity.ProductEntity
import com.bebridge.android_template.db.model.ProductWithCategoryAndTags
import com.bebridge.android_template.db.entity.ProductProductTagEntity
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(
  private val service: IApiService,
  private val db: AppDatabase
) {


}