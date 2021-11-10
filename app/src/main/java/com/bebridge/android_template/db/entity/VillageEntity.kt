package com.bebridge.android_template.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@Entity(tableName = "user")
@JsonSerializable
data class VillageEntity constructor(

    @PrimaryKey
    @Json(name = "id")
    val villageId: Long,

    @Json(name = "parishId")
    val parishId: Long,

    @Json(name = "village_name")
    var name: String?

)