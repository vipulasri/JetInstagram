package com.vipulasri.jetinstagram.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

val verticalPadding = 12.dp
val horizontalPadding = 10.dp
val bottomBarHeight = 50.dp

fun Modifier.icon() = this.size(24.dp)

fun Modifier.defaultPadding() = this.padding(
    horizontal = horizontalPadding,
    vertical = verticalPadding
)