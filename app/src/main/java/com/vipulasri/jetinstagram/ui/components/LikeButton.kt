package com.vipulasri.jetinstagram.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.contentColorFor
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

internal enum class AnimationState {
    START,
    MID,
    END
}

//dp animation
/*private val dpPropKey = DpPropKey()
private val dpAnimDefinition = transitionDefinition<Int> {
    transitionState(AnimationState.START.ordinal) { this[dpPropKey] = 24.dp }
    transitionState(AnimationState.MID.ordinal) { this[dpPropKey] = 14.dp }
    transitionState(AnimationState.END.ordinal) { this[dpPropKey] = 30.dp }

    transition(0 to 2, 1 to 2, 2 to 0) {
        dpPropKey using spring(
            stiffness = Spring.StiffnessHigh
        )
    }
}*/

@Composable
fun AnimLikeButton(
    post: Post,
    onLikeClick: (Post) -> Unit
) {
    val contentColor = contentColorFor(MaterialTheme.colors.background)
    var dpStartState by remember { mutableStateOf(AnimationState.START.ordinal) }
    var dpEndState by remember { mutableStateOf(AnimationState.START.ordinal) }
    var isAnimating by remember { mutableStateOf(false) }
    var likeIconRes by remember { mutableStateOf(R.drawable.ic_outlined_favorite) }
    var likeIconColor by remember { mutableStateOf(contentColor) }

/*    val dpAnim = transition(
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

                    isAnimating = false

                }
            }
        }
    )*/

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
            ImageBitmap.imageResource(id = likeIconRes), tint = likeIconColor,
//            modifier = Modifier.size(dpAnim[dpPropKey]),
            contentDescription = ""
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
