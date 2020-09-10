package com.vipulasri.jetinstagram.ui.components

import androidx.compose.animation.DpPropKey
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.core.tween
import androidx.compose.animation.transition
import androidx.compose.foundation.Box
import androidx.compose.foundation.ContentGravity
import androidx.compose.foundation.Icon
import androidx.compose.foundation.clickable
import androidx.compose.foundation.contentColor
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.material.ripple.RippleIndication
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.vipulasri.jetinstagram.R.drawable
import com.vipulasri.jetinstagram.model.Post

private enum class AnimationState {
  START,
  MID,
  END
}

//dp animation
private val dpPropKey = DpPropKey()
private val dpAnimDefinition = transitionDefinition<Int> {
  state(AnimationState.START.ordinal) { this[dpPropKey] = 24.dp }
  state(AnimationState.END.ordinal) { this[dpPropKey] = 30.dp }

  transition(0 to 2, 2 to 0) {
    dpPropKey using repeatable(
        iterations = 1,
        animation = tween(
            durationMillis = 50,
            easing = FastOutLinearInEasing
        )
    )
  }
}

@Composable
fun AnimLikeButton(
  post: Post,
  onLikeClick: (Post) -> Unit
) {
  var dpStartState by remember { mutableStateOf(0) }
  var dpEndState by remember { mutableStateOf(0) }

  val dpAnim = transition(
      definition = dpAnimDefinition,
      initState = dpStartState,
      toState = dpEndState,
      onStateChangeFinished = {
        when (it) {
          2 -> {
            dpStartState = 2
            dpEndState = 0
          }
        }
      }
  )

  val likeIcon =
    if (post.isLiked) imageResource(id = drawable.ic_filled_favorite) else imageResource(
        id = drawable.ic_outlined_favorite
    )

  val likeTint = if (post.isLiked) Color.Red else contentColor()

  Box(
      modifier = Modifier
          .clickable(
              onClick = {
                dpEndState = 2
                onLikeClick.invoke(post)
              },
              indication = RippleIndication(bounded = false, radius = 24.dp)
          )
          .padding(vertical = 10.dp)
          .then(Modifier.preferredSize(30.dp)),
      gravity = ContentGravity.Center
  ) {
    Icon(asset = likeIcon, tint = likeTint, modifier = Modifier.preferredSize(dpAnim[dpPropKey]))
  }

}

