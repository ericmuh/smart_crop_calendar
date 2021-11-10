package com.bebridge.android_template.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@Entity(tableName = "user")
@JsonSerializable
data class ParishEntity constructor(

    @PrimaryKey
    @Json(name = "id")
    val parishId: Long,

    @Json(name = "subcounty_id")
    var subcountyId: Long?,

    @Json(name = "parish_name")
    var name: String?

)