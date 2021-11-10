package com.bebridge.android_template.api.response

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class ApiParish(

  @Json(name = "id")
  val parishId: Long,

  val subcountId: Long,

  val name: String?
)
