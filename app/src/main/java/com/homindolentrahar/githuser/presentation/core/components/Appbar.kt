package com.homindolentrahar.githuser.presentation.core.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Appbar(
    title: @Composable () -> Unit = {
        Spacer(modifier = Modifier.width(0.dp))
    },
    navigationIcon: (@Composable () -> Unit)? = null,
    actions: @Composable (RowScope.() -> Unit) = {
        Spacer(modifier = Modifier.width(0.dp))
    }
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp,
        navigationIcon = navigationIcon,
        title = title,
        actions = actions,
    )
}