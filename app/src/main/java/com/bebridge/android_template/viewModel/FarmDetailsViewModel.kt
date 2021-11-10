package com.bebridge.android_template.viewModel

import androidx.databinding.ObservableField
import com.bebridge.android_template.api.response.ApiFarm
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FarmDetailsViewModel @Inject constructor() {

  val farms: ObservableField<List<ApiFarm>> = ObservableField()

  fun initialize() {
  }

}