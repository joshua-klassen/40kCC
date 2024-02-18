package com.example.a40kcc.data.repository

import androidx.annotation.WorkerThread
import com.example.a40kcc.data.dao.DeploymentDao
import com.example.a40kcc.data.`object`.Deployment

class DeploymentRepository(private val deploymentDao: DeploymentDao) {
    val allDeployments: List<Deployment> = deploymentDao.getAll()

    @WorkerThread
    fun getDeployment(deploymentId: Int): Deployment {
        return deploymentDao.getById(deploymentId)
    }

    @WorkerThread
    fun getDeployment(deploymentName: String): Deployment {
        return deploymentDao.getByName(deploymentName)
    }

    @WorkerThread
    fun getDeploymentByNickname(deploymentNickname: String): Deployment {
        return deploymentDao.getByNickname(deploymentNickname)
    }
}