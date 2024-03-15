package com.example.a40kcc.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.a40kcc.data.`object`.Deployment
import kotlinx.coroutines.flow.Flow

@Dao
interface DeploymentDao : BaseDao<Deployment> {
    @Query("SELECT * FROM deployment")
    fun getAll(): Flow<List<Deployment>>

    @Query("SELECT * FROM deployment WHERE deploymentID = :deploymentId")
    fun getById(deploymentId: Int): Flow<Deployment>

    @Query(
        "SELECT * FROM deployment WHERE name LIKE :deploymentName LIMIT 1"
    )
    fun getByName(deploymentName: String): Flow<Deployment>

    @Query(
        "SELECT * FROM deployment WHERE nickname LIKE :deploymentNickname LIMIT 1"
    )
    fun getByNickname(deploymentNickname: String): Flow<Deployment>
}