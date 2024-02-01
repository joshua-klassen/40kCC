package com.example.a40kcc.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.a40kcc.data.`object`.Deployment

@Dao
interface DeploymentDao {
    @Query("SELECT * FROM deployment")
    fun getAll(): List<Deployment>

    @Query("SELECT * FROM deployment WHERE deploymentID = :deploymentId")
    fun getById(deploymentId: Int): Deployment

    @Query(
        "SELECT * FROM deployment WHERE name LIKE :name LIMIT 1"
    )
    fun getByName(name: String): Deployment

    @Query(
        "SELECT * FROM deployment WHERE nickname LIKE :nickname LIMIT 1"
    )
    fun getByNickname(nickname: String): Deployment

    @Insert
    suspend fun insert(vararg deployment: Deployment)
}