package com.example.a40kcc.ui.utilities

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class ErrorHandling(
    private var snackbarHostState: SnackbarHostState,
    var modifier: Modifier = Modifier
) {
    fun showSnackbar(
        message: String,
        duration: SnackbarDuration,
        actionLabel: String? = null,
        withDismissAction: Boolean = false
    ) {
        provideCoroutineScope().launch {
            snackbarHostState.showSnackbar(
                message = message,
                actionLabel = actionLabel,
                withDismissAction = withDismissAction,
                duration = duration
            )
        }
    }

    private fun provideCoroutineScope(): CoroutineScope {
        return CoroutineScope(context = Dispatchers.Main + SupervisorJob())
    }

    fun provideCoroutineExceptionScope(
        errorMessage: String
    ): CoroutineScope {
        return CoroutineScope(
            context = Dispatchers.Main + SupervisorJob() + CoroutineExceptionHandler { _, exception ->
                println("$errorMessage:\n $exception")
                showSnackbar(
                    message = errorMessage,
                    duration = SnackbarDuration.Indefinite,
                    withDismissAction = true
                )
            }
        )
    }
}

