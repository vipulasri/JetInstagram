package com.vipulasri.jetinstagram.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
              onClick = onClick
          )
          .padding(vertical = 10.dp, horizontal = 5.dp)
          .indication(
              indication = rememberRipple(bounded = false, radius = 24.dp),
              interactionSource = remember { MutableInteractionSource() }
          )
          .then(Modifier.size(24.dp)),
      contentAlignment = Alignment.Center
  ){
      icon()
  }
}