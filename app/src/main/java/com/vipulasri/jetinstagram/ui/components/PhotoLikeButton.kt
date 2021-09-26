package com.vipulasri.jetinstagram.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.imageResource
import com.vipulasri.jetinstagram.R

@Composable
fun DoubleTapPhotoLikeAnimation(onDoubleTap: () -> Unit) {
  // Creates a transition state that starts in [Disappeared] State
  var transitionState by remember {
    mutableStateOf(MutableTransitionState(LikedStates.Disappeared))
  }

  Box(
    Modifier
      .fillMaxSize()
      .pointerInput(Unit) {
        detectTapGestures(
          onDoubleTap = {
            // This creates a new `MutableTransitionState` object. When a new
            // `MutableTransitionState` object gets passed to `updateTransition`, a
            // new transition will be created. All existing values, velocities will
            // be lost as a result. Hence, in most cases, this is not recommended.
            // The exception is when it's more important to respond immediately to
            // user interaction than preserving continuity.
            transitionState = MutableTransitionState(LikedStates.Initial)
            onDoubleTap.invoke()
          }
        )
      }
  ) {
    // This ensures sequential states: Initial -> Liked -> Disappeared
    if (transitionState.currentState == LikedStates.Initial) {
      transitionState.targetState = LikedStates.Liked
    } else if (transitionState.currentState == LikedStates.Liked) {
      // currentState will be updated to targetState when the transition is finished, so
      // it can be used as a signal to start the next transition.
      transitionState.targetState = LikedStates.Disappeared
    }

    // Creates a transition using the TransitionState object that gets recreated at each
    // double tap.
    val transition = updateTransition(transitionState, label = "")
    // Creates an alpha animation, as a part of the transition.
    val alpha by transition.animateFloat(
      transitionSpec = {
        when {
          // Uses different animation specs for transitioning from/to different states
          LikedStates.Initial isTransitioningTo LikedStates.Liked ->
            keyframes {
              durationMillis = 500
              0f at 0 // optional
              0.5f at 100
              1f at 225 // optional
            }
          LikedStates.Liked isTransitioningTo LikedStates.Disappeared ->
            tween(durationMillis = 200)
          else -> snap()
        }
      }, label = ""
    ) {
      if (it == LikedStates.Liked) 1f else 0f
    }

    // Creates a scale animation, as a part of the transition
    val scale by transition.animateFloat(
      transitionSpec = {
        when {
          // Uses different animation specs for transitioning from/to different states
          LikedStates.Initial isTransitioningTo LikedStates.Liked ->
            spring(dampingRatio = 0.4f)
          LikedStates.Liked isTransitioningTo LikedStates.Disappeared ->
            tween(200)
          else -> snap()
        }
      }, label = ""
    ) {
      when (it) {
        LikedStates.Initial -> 0f
        LikedStates.Liked -> 3f
        LikedStates.Disappeared -> 1f
      }
    }

    Icon(
      ImageBitmap.imageResource(id = R.drawable.ic_filled_favorite),
      "Like",
      Modifier
        .align(Alignment.Center)
        .graphicsLayer(
          alpha = alpha,
          scaleX = scale,
          scaleY = scale
        ),
      tint = Color.White
    )
  }
}

enum class LikedStates {
  Initial,
  Liked,
  Disappeared
}
