package com.example.a40kcc.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.example.a40kcc.data.model.DeploymentViewModel
import com.example.a40kcc.data.`object`.Deployment

@Composable
fun DeploymentScreen(deploymentViewModel: DeploymentViewModel, onBackClick: () -> Unit) {
    Column {
        Button(onClick = onBackClick) {
            Column {
                Text("Back")
            }
        }
        val deployments: List<Deployment>? =
            deploymentViewModel.allDeployments.observeAsState().value

        if (deployments != null) {
            DeploymentScreen(deployments)
        }
    }
}

@Composable
fun DeploymentScreen(deployments: List<Deployment>) {
    println(deployments.size.toString())
}