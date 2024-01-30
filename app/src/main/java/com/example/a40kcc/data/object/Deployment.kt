package com.example.a40kcc.data.`object`

import android.media.Image
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("deployment")
data class Deployment(
    @PrimaryKey(true) val deploymentID: Int,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("nickname") val nickname: String?,
    @ColumnInfo("image") val image: Image?
)