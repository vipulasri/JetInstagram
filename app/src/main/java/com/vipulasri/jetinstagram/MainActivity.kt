package com.vipulasri.jetinstagram

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.platform.setContent
import com.vipulasri.jetinstagram.ui.MainScreen
import com.vipulasri.jetinstagram.ui.theme.JetInstagramTheme

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      JetInstagramTheme {
        Surface(color = MaterialTheme.colors.background) {
          MainScreen()
        }
      }
    }
  }
}