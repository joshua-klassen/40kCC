package com.example.a40kcc.data.`object`

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("objective")
data class Objective(
    @PrimaryKey(true) val objectiveID: Int,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("nickname") val nickname: String?
)