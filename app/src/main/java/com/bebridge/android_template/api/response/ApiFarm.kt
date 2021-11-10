package com.bebridge.android_template.api.response

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class ApiFarm(

  @Json(name = "id")
  val farmId: Long,

  val name: String?
)
