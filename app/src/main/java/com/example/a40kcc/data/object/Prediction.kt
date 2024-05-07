package com.example.a40kcc.data.`object`

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.a40kcc.COLORS

@Entity(
    tableName = "prediction",
    indices = [Index(value = ["predictionID"])]
)
data class Prediction(
    @PrimaryKey(autoGenerate = true) val predictionID: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "color") val color: Long,
    @ColumnInfo(name = "minimum_points") val minPoints: Int,
    @ColumnInfo(name = "maximum_points") val maxPoints: Int,
    @ColumnInfo(name = "default_option") val defaultOption: Boolean = false
) : CoreObject {
    override fun getCoreColumns(): Map<String, String> {
        return mapOf(
            Pair("Name", name),
            Pair("Color", COLORS.filterValues { it == color }.keys.toString())
        )
    }

    override fun getDetailColumns(): Map<String, String> {
        return mapOf(
            Pair("Minimum Points", minPoints.toString()),
            Pair("Maximum Points", maxPoints.toString())
        )
    }

    override fun getDisplayName(): String {
        return name
    }
}