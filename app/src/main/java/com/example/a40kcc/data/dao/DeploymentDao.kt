package com.example.a40kcc.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.a40kcc.data.`object`.Deployment

@Dao
interface DeploymentDao {
    @Query("SELECT * FROM deployment")
    fun getAll(): List<Deployment>

    @Query("SELECT * FROM deployment WHERE deploymentID = :deploymentId")
    fun getById(deploymentId: Int): Deployment

    @Query(
        "SELECT * FROM deployment WHERE name LIKE :deploymentName LIMIT 1"
    )
    fun getByName(deploymentName: String): Deployment

    @Query(
        "SELECT * FROM deployment WHERE nickname LIKE :deploymentNickname LIMIT 1"
    )
    fun getByNickname(deploymentNickname: String): Deployment

    @Insert
    suspend fun insert(vararg deployment: Deployment)

    @Update
    fun update(vararg deployment: Deployment)

    @Query("DELETE FROM deployment WHERE deploymentID = :deploymentId")
    fun delete(deploymentId: Int)

    @Query("DELETE FROM deployment")
    suspend fun deleteAll()
}