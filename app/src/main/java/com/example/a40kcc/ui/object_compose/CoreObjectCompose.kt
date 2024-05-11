package com.example.a40kcc.ui.object_compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.a40kcc.data.`object`.CoreObject

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
        navController: NavController,
        modifier: Modifier = Modifier,
        onDismissRequest: () -> Unit
    ) {
    }

    @Composable
    fun EditObject(
        coreObject: CoreObject,
        navController: NavController,
        modifier: Modifier = Modifier,
        onDismissRequest: () -> Unit
    ) {
    }

    @Composable
    fun RemoveObject(
        coreObject: CoreObject,
        navController: NavController,
        modifier: Modifier = Modifier,
        onDismissRequest: () -> Unit
    ) {
    }
}