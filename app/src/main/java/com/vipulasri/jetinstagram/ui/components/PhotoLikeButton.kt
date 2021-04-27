package com.vipulasri.jetinstagram.ui.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.contentColorFor
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
import androidx.compose.ui.unit.dp
import com.vipulasri.jetinstagram.R

@Composable
fun PhotoLikeAnimation(
  modifier: Modifier,
  startAnimation: Boolean,
  onAnimationComplete: () -> Unit) {
    var animationState by remember { mutableStateOf(AnimationState.START) }
    val transition = updateTransition(targetState = animationState, label = "")

    val size by transition.animateDp(
        transitionSpec = { spring(dampingRatio = Spring.DampingRatioHighBouncy) }, label = ""
    ) { state ->
        when (state) {
            AnimationState.START -> 0.dp
            else -> 100.dp
        }
    }

    if (size > 0.dp){
        Box(modifier = modifier) {
            Icon(
                ImageBitmap.imageResource(id = R.drawable.ic_filled_favorite),
                tint = contentColorFor(MaterialTheme.colors.background),
                modifier = Modifier
                    .size(size)
                    .align(Alignment.Center),
                contentDescription = ""
            )
        }
    }

    if (startAnimation && !transition.isRunning) {
        animationState = AnimationState.END
    }
    else{
        onAnimationComplete.invoke()
    }

    if (transition.currentState == AnimationState.END){
        animationState = AnimationState.START
    }

}
