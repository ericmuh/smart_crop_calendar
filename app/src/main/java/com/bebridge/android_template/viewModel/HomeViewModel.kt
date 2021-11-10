package com.bebridge.android_template.viewModel

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.bebridge.android_template.api.response.ApiFarm
import com.bebridge.android_template.db.entity.ProductEntity
import com.bebridge.android_template.fragment.HomeNavigator
import com.bebridge.android_template.repository.ProductRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeViewModel @Inject constructor(private val productRepository: ProductRepository) {

  val products = ObservableField<List<ProductEntity>>()

  var navigator: HomeNavigator? = null

  val loading = ObservableBoolean(false)

  val farms: ObservableField<List<ApiFarm>> = ObservableField()

  fun initialize(navigator: HomeNavigator) {
    this.navigator = navigator
    farms.set(listOf(ApiFarm(1, "Brian's Crop Farm"), ApiFarm(2, "Matooke Crop Farm")))
  }

}