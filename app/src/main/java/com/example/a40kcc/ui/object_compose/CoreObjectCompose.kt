package com.example.a40kcc.ui.object_compose

import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import com.example.a40kcc.data.`object`.CoreObject
import com.example.a40kcc.ui.utilities.ComposeData
import kotlinx.coroutines.CoroutineExceptionHandler
import java.lang.Thread.sleep

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
        onDismissRequest: () -> Unit
    ) {
    }

    @Composable
    fun EditObject(
        coreObject: CoreObject,
        composeData: ComposeData,
        onDismissRequest: () -> Unit
    ) {
    }

    @Composable
    fun RemoveObject(
        coreObject: CoreObject,
        composeData: ComposeData,
        onDismissRequest: () -> Unit
    ) {
    }

    fun getExceptionHandler(
        errorMessage: String,
        composeData: ComposeData,
        continueRun: Boolean = false
    ): CoroutineExceptionHandler {
        return CoroutineExceptionHandler { _, exception ->
            println("$errorMessage:\n $exception")
            composeData.showSnackbar(
                message = errorMessage,
                duration = SnackbarDuration.Indefinite,
                withDismissAction = true
            )
            if (!continueRun) {
                sleep(3000)
                throw RuntimeException(errorMessage, exception)
            }
        }
    }
}