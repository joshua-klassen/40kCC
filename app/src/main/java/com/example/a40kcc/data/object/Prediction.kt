package com.example.a40kcc.data.`object`

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("prediction")
data class Prediction(
    @PrimaryKey(true) val predictionID: Int,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("color") val color: String,
    @ColumnInfo("minimum_points") val minPoints: Int,
    @ColumnInfo("maximum_points") val maxPoints: Int
)