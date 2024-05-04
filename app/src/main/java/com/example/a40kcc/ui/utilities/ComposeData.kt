package com.example.a40kcc.ui.utilities

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ComposeData {
    var modifier: Modifier = Modifier
    private var snackbarHostState: SnackbarHostState? = null
    private var coroutineScope: CoroutineScope? = null

    fun setSnackbarHostState(
        newSnackbarHostState: SnackbarHostState,
        newCoroutineScope: CoroutineScope
    ) {
        snackbarHostState = newSnackbarHostState
        coroutineScope = newCoroutineScope
    }

    @Suppress("unused")
    fun getScope(): CoroutineScope? {
        return coroutineScope
    }

    @Suppress("unused")
    fun getSnackbarHostState(): SnackbarHostState? {
        return snackbarHostState
    }

    fun showSnackbar(
        message: String,
        duration: SnackbarDuration,
        actionLabel: String? = null,
        withDismissAction: Boolean = false
    ) {
        if (coroutineScope == null) {
            throw NullPointerException("Cannot create snackbar message as the scope is null")
        }

        coroutineScope?.let { scope ->
            scope.launch {
                if (snackbarHostState == null) {
                    throw NullPointerException("Cannot create snackbar message as the snackbar host state is null")
                }
                snackbarHostState?.showSnackbar(
                    message = message,
                    actionLabel = actionLabel,
                    withDismissAction = withDismissAction,
                    duration = duration
                )
            }
        }
    }
}