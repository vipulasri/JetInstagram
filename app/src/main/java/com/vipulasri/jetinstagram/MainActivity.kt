package com.vipulasri.jetinstagram

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.vipulasri.jetinstagram.ui.MainScreen
import com.vipulasri.jetinstagram.ui.theme.JetInstagramTheme

class MainActivity : AppCompatActivity() {

  @ExperimentalFoundationApi
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    this.setContent {
      JetInstagramTheme {
        Surface(color = MaterialTheme.colors.background) {
          MainScreen()
        }
      }
    }
  }

}