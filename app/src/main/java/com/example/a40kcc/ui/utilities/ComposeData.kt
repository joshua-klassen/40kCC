package com.example.a40kcc.ui.utilities

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun getExceptionHandler(
    errorMessage: String,
    continueRun: Boolean = true
): CoroutineExceptionHandler {
    return CoroutineExceptionHandler { _, exception ->
        println("$errorMessage:\n $exception")
        if (!continueRun) {
            Thread.sleep(3000)
            throw RuntimeException(errorMessage, exception)
        }
    }
}

class ComposeData(
    private var snackbarHostState: SnackbarHostState,
    private var coroutineScope: CoroutineScope,
    var modifier: Modifier = Modifier
) {
    fun getScope(): CoroutineScope {
        return coroutineScope
    }

    fun showSnackbar(
        message: String,
        duration: SnackbarDuration,
        actionLabel: String? = null,
        withDismissAction: Boolean = false
    ) {
        coroutineScope.launch {
            snackbarHostState.showSnackbar(
                message = message,
                actionLabel = actionLabel,
                withDismissAction = withDismissAction,
                duration = duration
            )
        }
    }

    fun getExceptionHandler(
        errorMessage: String,
        continueRun: Boolean = true
    ): CoroutineExceptionHandler {
        return CoroutineExceptionHandler { _, exception ->
            println("$errorMessage:\n $exception")
            this.showSnackbar(
                message = errorMessage,
                duration = SnackbarDuration.Indefinite,
                withDismissAction = true
            )
            if (!continueRun) {
                Thread.sleep(3000)
                throw RuntimeException(errorMessage, exception)
            }
        }
    }
}