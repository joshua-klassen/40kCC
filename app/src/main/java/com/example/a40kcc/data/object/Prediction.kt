package com.example.a40kcc.data.`object`

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    "prediction",
    indices = [Index(value = ["predictionID"])]
)
data class Prediction(
    @PrimaryKey(true) val predictionID: Int = 0,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("color") val color: Long,
    @ColumnInfo("minimum_points") val minPoints: Int,
    @ColumnInfo("maximum_points") val maxPoints: Int,
    @ColumnInfo("default_option") val defaultOption: Boolean = false
) : CoreObject {
    override fun getCoreColumns(): Map<String, String> {
        return mapOf(
            Pair("Name", name),
            Pair("Color", color.toString())
        )
    }

    override fun getDetailColumns(): Map<String, String> {
        return mapOf(
            Pair("Minimum Points", minPoints.toString()),
            Pair("Maximum Points", maxPoints.toString())
        )
    }
}