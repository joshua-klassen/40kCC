package com.example.a40kcc.data.repository

import androidx.annotation.WorkerThread
import com.example.a40kcc.data.dao.DeploymentDao
import com.example.a40kcc.data.`object`.Deployment
import kotlinx.coroutines.flow.Flow

class DeploymentRepository(private val deploymentDao: DeploymentDao) {
    val allDeployments: Flow<List<Deployment>> = deploymentDao.getAll()

    @WorkerThread
    fun getDeployment(deploymentId: Int): Flow<Deployment> {
        return deploymentDao.getById(deploymentId)
    }

    @WorkerThread
    fun getDeployment(deploymentName: String): Flow<Deployment> {
        return deploymentDao.getByName(deploymentName)
    }

    @WorkerThread
    fun getDeploymentByNickname(deploymentNickname: String): Flow<Deployment> {
        return deploymentDao.getByNickname(deploymentNickname)
    }
}