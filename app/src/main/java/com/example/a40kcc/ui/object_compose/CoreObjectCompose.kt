package com.example.a40kcc.ui.object_compose

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.a40kcc.data.`object`.CoreObject
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
        modifier: Modifier,
        onDismissRequest: () -> Unit
    ) {
    }

    @Composable
    fun EditObject(
        coreObject: CoreObject,
        modifier: Modifier,
        onDismissRequest: () -> Unit
    ) {
    }

    @Composable
    fun RemoveObject(
        coreObject: CoreObject,
        modifier: Modifier,
        onDismissRequest: () -> Unit
    ) {
    }

    fun getExceptionHandler(
        errorMessage: String,
        context: Context,
        continueRun: Boolean = false
    ): CoroutineExceptionHandler {
        return CoroutineExceptionHandler { _, exception ->
            println("$errorMessage:\n $exception")
            Toast.makeText(
                context,
                errorMessage,
                Toast.LENGTH_LONG
            ).show()
            if (!continueRun) {
                sleep(3000)
                throw RuntimeException(errorMessage, exception)
            }
        }
    }
}