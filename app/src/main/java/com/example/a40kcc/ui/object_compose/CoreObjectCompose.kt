package com.example.a40kcc.ui.object_compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.a40kcc.data.`object`.CoreObject
import com.example.a40kcc.ui.utilities.ComposeData
import kotlinx.coroutines.CoroutineExceptionHandler

interface CoreObjectCompose {
    fun canAdd(): Boolean {
        return true
    }

    fun canEdit(coreObject: CoreObject): Boolean {
        return true
    }

    fun canRemove(coreObject: CoreObject): Boolean {
        return true
    }

    @Composable
    fun AddObject(
        composeData: ComposeData,
        navController: NavController,
        onDismissRequest: () -> Unit
    ) {
    }

    @Composable
    fun EditObject(
        coreObject: CoreObject,
        composeData: ComposeData,
        navController: NavController,
        onDismissRequest: () -> Unit
    ) {
    }

    @Composable
    fun RemoveObject(
        coreObject: CoreObject,
        composeData: ComposeData,
        navController: NavController,
        onDismissRequest: () -> Unit
    ) {
    }

    fun getExceptionHandler(
        errorMessage: String,
        composeData: ComposeData,
        continueRun: Boolean = false
    ): CoroutineExceptionHandler {
        return getExceptionHandler(
            errorMessage = errorMessage,
            composeData = composeData,
            continueRun = continueRun
        )
    }
}