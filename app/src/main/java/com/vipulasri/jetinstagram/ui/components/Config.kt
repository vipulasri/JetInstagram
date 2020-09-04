package com.vipulasri.jetinstagram.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

val verticalPadding = 12.dp
val horizontalPadding = 10.dp

fun Modifier.icon() = this.width(20.dp).height(20.dp)

fun Modifier.defaultPadding() = this.padding(horizontal = horizontalPadding,
    vertical = verticalPadding)