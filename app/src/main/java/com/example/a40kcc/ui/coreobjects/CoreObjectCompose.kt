package com.example.a40kcc.ui.coreobjects

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.a40kcc.data.`object`.CoreObject

interface CoreObjectCompose {
    fun canAdd(): Boolean {
        return true
    }

    fun canEdit(): Boolean {
        return true
    }

    fun canRemove(): Boolean {
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
}