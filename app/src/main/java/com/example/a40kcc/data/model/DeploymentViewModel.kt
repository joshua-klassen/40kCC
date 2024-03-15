package com.example.a40kcc.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.a40kcc.data.`object`.Deployment
import com.example.a40kcc.data.repository.DeploymentRepository

class DeploymentViewModel(deploymentRepository: DeploymentRepository) : ViewModel() {
    val allDeployments: LiveData<List<Deployment>> =
        deploymentRepository.allDeployments.asLiveData()
}

class DeploymentViewModelFactory(private val deploymentRepository: DeploymentRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DeploymentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DeploymentViewModel(deploymentRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}