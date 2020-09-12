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
import androidx.ui.tooling.preview.Preview
import com.vipulasri.jetinstagram.R
import com.vipulasri.jetinstagram.model.Post
import com.vipulasri.jetinstagram.model.names

private enum class AnimationState {
  START,
  MID,
  END
}

//dp animation
private val dpPropKey = DpPropKey()
private val dpAnimDefinition = transitionDefinition<Int> {
  state(AnimationState.START.ordinal) { this[dpPropKey] = 24.dp }
  state(AnimationState.MID.ordinal) { this[dpPropKey] = 14.dp }
  state(AnimationState.END.ordinal) { this[dpPropKey] = 30.dp }

  transition(0 to 2, 1 to 2, 2 to 0) {
    dpPropKey using spring(
        stiffness = Spring.StiffnessHigh
    )
  }
}

@Composable
fun AnimLikeButton(
  post: Post,
  onLikeClick: (Post) -> Unit
) {
  val contentColor = contentColor()
  var dpStartState by remember { mutableStateOf(AnimationState.START.ordinal) }
  var dpEndState by remember { mutableStateOf(AnimationState.START.ordinal) }
  var isAnimating by remember { mutableStateOf(true) }
  var likeIconRes by remember { mutableStateOf(R.drawable.ic_outlined_favorite) }
  var likeIconColor by remember { mutableStateOf(contentColor) }

  val dpAnim = transition(
      definition = dpAnimDefinition,
      initState = dpStartState,
      toState = dpEndState,
      onStateChangeFinished = {
        when (it) {
          AnimationState.MID.ordinal -> {
            dpStartState = AnimationState.MID.ordinal
            dpEndState = AnimationState.END.ordinal
          }
          AnimationState.END.ordinal -> {
            dpStartState = AnimationState.END.ordinal
            dpEndState = AnimationState.START.ordinal

            likeIconRes = if (post.isLiked) {
              R.drawable.ic_filled_favorite
            } else {
              R.drawable.ic_outlined_favorite
            }

            likeIconColor = if (post.isLiked) {
              Color.Red
            } else {
              contentColor
            }

          }
        }
      }
  )

  if (!isAnimating) {

    likeIconRes = if (post.isLiked) {
      R.drawable.ic_filled_favorite
    } else {
      R.drawable.ic_outlined_favorite
    }

    likeIconColor = if (post.isLiked) {
      Color.Red
    } else {
      contentColor
    }
  }

  Box(
      modifier = Modifier
          .clickable(
              onClick = {
                dpEndState = AnimationState.MID.ordinal
                isAnimating = true
                onLikeClick.invoke(post)
              },
              indication = RippleIndication(bounded = false, radius = 24.dp)
          )
          .padding(vertical = 10.dp)
          .then(Modifier.preferredSize(30.dp)),
      gravity = ContentGravity.Center
  ) {
    Icon(
        asset = imageResource(id = likeIconRes), tint = likeIconColor,
        modifier = Modifier.preferredSize(dpAnim[dpPropKey])
    )
  }

}

@Preview
@Composable
private fun LikeButtonPreview() {
  AnimLikeButton(
      post = Post(
          id = 1,
          image = "https://source.unsplash.com/random/400x300",
          userName = names.first(),
          userImage = "https://randomuser.me/api/portraits/men/1.jpg",
          likesCount = 100,
          commentsCount = 20,
          timeStamp = System.currentTimeMillis() - (60000)
      ),
      onLikeClick = {
      })
}
