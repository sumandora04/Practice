package com.notepoint.practice.network

//import com.google.gson.annotations.SerializedName
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json


@Entity(tableName = "heroes_table")
data class Heroes(
    @PrimaryKey(autoGenerate = true)
    val id:Long =0L,
    @Json(name = "name") val heroName:String,
    @Json(name = "bio") val bioData:String
) {
}