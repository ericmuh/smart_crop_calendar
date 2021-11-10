package com.bebridge.android_template.api.response

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class ApiDistrict(

  @Json(name = "id")
  val districtId: Long,

  val name: String?
)
