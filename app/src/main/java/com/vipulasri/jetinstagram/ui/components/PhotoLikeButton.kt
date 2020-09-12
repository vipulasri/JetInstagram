package com.vipulasri.jetinstagram.ui.components

import androidx.compose.animation.DpPropKey
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.transition
import androidx.compose.foundation.Box
import androidx.compose.foundation.ContentGravity
import androidx.compose.foundation.Icon
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.ColumnScope.gravity
import androidx.compose.foundation.layout.Stack
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.vipulasri.jetinstagram.R

//dp animation
private val dpPropKey = DpPropKey()
private val dpAnimDefinition = transitionDefinition<Int> {
  state(AnimationState.START.ordinal) { this[dpPropKey] = 0.dp }
  state(AnimationState.END.ordinal) { this[dpPropKey] = 100.dp }

  transition(0 to 2, 2 to 0) {
    dpPropKey using spring(
        stiffness = Spring.StiffnessMedium
    )
  }
}

@Composable
fun PhotoLikeAnimation(
  modifier: Modifier,
  startAnimation: Boolean,
  onAnimationComplete: () -> Unit) {
  var dpStartState by remember { mutableStateOf(AnimationState.START.ordinal) }
  var dpEndState by remember { mutableStateOf(AnimationState.START.ordinal) }
  var isAnimating by remember { mutableStateOf(false) }

  val dpAnim = transition(
      definition = dpAnimDefinition,
      initState = dpStartState,
      toState = dpEndState,
      onStateChangeFinished = {
        when (it) {
          AnimationState.END.ordinal -> {
            dpStartState = AnimationState.END.ordinal
            dpEndState = AnimationState.START.ordinal

            isAnimating = false
            onAnimationComplete.invoke()
          }
        }
      }
  )

  Stack(modifier = modifier) {
    Icon(
        asset = imageResource(id = R.drawable.ic_filled_favorite),
        tint = Color.White,
        modifier = Modifier.preferredSize(dpAnim[dpPropKey])
            .gravity(Alignment.Center)
    )
  }

  if (startAnimation && !isAnimating) {
    dpEndState = AnimationState.END.ordinal
    isAnimating = true
  }

}
