package com.vipulasri.jetinstagram.ui.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
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
import androidx.ui.tooling.preview.Preview
import com.vipulasri.jetinstagram.R
import com.vipulasri.jetinstagram.model.Post
import com.vipulasri.jetinstagram.model.User
import com.vipulasri.jetinstagram.model.names

enum class AnimationState {
    START, END
}

@Composable
fun AnimLikeButton(
    post: Post,
    onLikeClick: (Post) -> Unit
) {
    var likeIconRes by remember { mutableStateOf(R.drawable.ic_outlined_favorite) }
    var animationState by remember { mutableStateOf(AnimationState.START) }
    val startColor = contentColorFor(MaterialTheme.colors.background)
    val endColor = Color(0xFFDF0707)

    val transition = updateTransition(targetState = animationState, label = "")

    val animatedColor by transition.animateColor(
        transitionSpec = { spring(dampingRatio = Spring.DampingRatioHighBouncy) }, label = ""
    ) { state ->
        when (state) {
            AnimationState.START -> startColor
            else -> endColor
        }
    }

    val size by transition.animateDp(
        transitionSpec = { spring(dampingRatio = Spring.DampingRatioHighBouncy) }, label = ""
    ) { state ->
        when (state) {
            AnimationState.START -> 24.dp
            else -> 26.dp
        }
    }

    likeIconRes = if (post.isLiked) {
        R.drawable.ic_filled_favorite
    } else {
        R.drawable.ic_outlined_favorite
    }

    Box(
        modifier = Modifier
            .clickable(
                onClick = {

                    animationState = when (animationState) {
                        AnimationState.START -> AnimationState.END
                        else -> AnimationState.START
                    }

                    onLikeClick.invoke(post)
                }
            )
            .indication(
                indication = rememberRipple(bounded = false, radius = 24.dp),
                interactionSource = remember { MutableInteractionSource() }
            )
            .padding(vertical = 10.dp)
            .then(Modifier.size(30.dp)),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            ImageBitmap.imageResource(id = likeIconRes), tint = animatedColor,
            modifier = Modifier.size(size),
            contentDescription = ""
        )
    }

/*    FloatingActionButton(
        backgroundColor = animatedColor,
        modifier = Modifier.size(40.dp),
        onClick = {
            animationState = when (animationState) {
                AnimationState.START -> AnimationState.END
                else -> AnimationState.START
            }
        }) {
        Icon(
            ImageBitmap.imageResource(id = R.drawable.ic_outlined_favorite), tint = animatedColor,
            modifier = Modifier.size(position),
            contentDescription = ""
        )
    }*/
}

@Preview
@Composable
private fun LikeButtonPreview() {
    AnimLikeButton(
        post = Post(
            id = 1,
            image = "https://source.unsplash.com/random/400x300",
            user = User(
                name = names.first(),
                username = names.first(),
                image = "https://randomuser.me/api/portraits/men/1.jpg"
            ),
            likesCount = 100,
            commentsCount = 20,
            timeStamp = System.currentTimeMillis() - (60000)
        ),
        onLikeClick = {
        })
}