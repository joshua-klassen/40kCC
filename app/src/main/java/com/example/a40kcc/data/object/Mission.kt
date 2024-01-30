package com.example.a40kcc.data.`object`

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("mission")
data class Mission(
    @PrimaryKey(true) val missionID: Int,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("nickname") val nickname: String?
)