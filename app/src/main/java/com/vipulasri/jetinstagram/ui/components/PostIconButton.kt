package com.vipulasri.jetinstagram.ui.components

import androidx.compose.foundation.Box
import androidx.compose.foundation.ContentGravity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.material.ripple.RippleIndication
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PostIconButton(
  onClick: () -> Unit = { },
  icon: @Composable () -> Unit
) {
  Box(
      modifier = Modifier
          .clickable(
              onClick = onClick,
              indication = RippleIndication(bounded = false, radius = 24.dp)
          )
          .padding(vertical = 10.dp, horizontal = 5.dp)
          .then(Modifier.preferredSize(24.dp)),
      gravity = ContentGravity.Center,
      children = icon
  )
}