package com.bebridge.android_template.api.response

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class ApiSubcounty(

  @Json(name = "id")
  val subcountyId: Long,

  val districtId: Long,

  val name: String?
)
