package com.bebridge.android_template.api.response

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class ApiVillage(

  @Json(name = "id")
  val villageId: Long,

  val parishId: Long,

  val name: String?
)
