package com.vipulasri.jetinstagram.ui.components

import android.net.Uri
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.onDispose
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

@Composable
fun VideoPlayer(uri: Uri) {
  val context = ContextAmbient.current

  val exoPlayer = remember {
    SimpleExoPlayer.Builder(context)
        .build()
        .apply {
          val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(
              context,
              Util.getUserAgent(context, context.packageName)
          )

          val source = ProgressiveMediaSource.Factory(dataSourceFactory)
              .createMediaSource(uri)

          this.prepare(source)
        }
  }

  exoPlayer.playWhenReady = true
  exoPlayer.repeatMode = Player.REPEAT_MODE_ONE

  AndroidView(viewBlock = { context ->
    PlayerView(context).apply {
      hideController()
      useController = false

      player = exoPlayer
      layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
    }
  })

  onDispose(callback = {
    exoPlayer.stop()
  })

}