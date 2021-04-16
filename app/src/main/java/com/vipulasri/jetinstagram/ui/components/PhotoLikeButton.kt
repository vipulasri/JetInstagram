package com.vipulasri.jetinstagram.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import com.vipulasri.jetinstagram.R

//dp animation
/*private val dpPropKey = DpPropKey()
private val dpAnimDefinition = transitionDefinition {
  state(AnimationState.START.ordinal) { this[dpPropKey] = 0.dp }
  state(AnimationState.END.ordinal) { this[dpPropKey] = 100.dp }

  transition(0 to 2, 2 to 0) {
    dpPropKey using spring(
        stiffness = Spring.StiffnessMedium
    )
  }
}*/

@Composable
fun PhotoLikeAnimation(
  modifier: Modifier,
  startAnimation: Boolean,
  onAnimationComplete: () -> Unit) {
  var dpStartState by remember { mutableStateOf(AnimationState.START.ordinal) }
  var dpEndState by remember { mutableStateOf(AnimationState.START.ordinal) }
  var isAnimating by remember { mutableStateOf(false) }

/*  val dpAnim = transition(
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
  )*/

  Box(modifier = modifier) {
    Icon(
        ImageBitmap.imageResource(id = R.drawable.ic_filled_favorite),
        tint = Color.White,
        modifier = Modifier
//            .size(dpAnim[dpPropKey])
            .align(Alignment.Center),
        contentDescription = ""
    )
  }

  if (startAnimation && !isAnimating) {
    dpEndState = AnimationState.END.ordinal
    isAnimating = true
  }

}
