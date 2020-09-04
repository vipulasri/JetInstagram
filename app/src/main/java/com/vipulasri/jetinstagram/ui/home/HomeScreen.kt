package com.vipulasri.jetinstagram.ui.home

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Box
import androidx.compose.foundation.ContentGravity
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.Modifier.Companion
import androidx.compose.ui.graphics.ImageAsset
import androidx.compose.ui.graphics.imageFromResource
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vipulasri.jetinstagram.R

@Composable
fun HomeScreen() {
  val (currentSection, setCurrentSection) = savedInstanceState { HomeSection.Home }
  val navItems = HomeSection.values()
      .toList()
  Scaffold(
      bottomBar = {
        BottomBar(
            items = navItems,
            currentSection = currentSection,
            onSectionSelected = setCurrentSection,
        )
      }) { innerPadding ->
    val modifier = Modifier.padding(innerPadding)
    Crossfade(currentSection) { section ->
      when (section) {
        HomeSection.Home -> Home()
        HomeSection.Reels -> Content(title = "Reels")
        HomeSection.Favorite -> Content(title = "Favorite")
        HomeSection.Profile -> Content(title = "Profile")
      }
    }
  }
}

@Composable
private fun Content(title: String) {
  Box(
      modifier = Modifier.fillMaxSize(),
      gravity = ContentGravity.Center
  ) {
    Text(
        text = title,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.h5)
  }
}

@Composable
private fun BottomBar(
  items: List<HomeSection>,
  currentSection: HomeSection,
  onSectionSelected: (HomeSection) -> Unit,
) {
  BottomNavigation(
      modifier = Modifier.height(45.dp),
      backgroundColor = MaterialTheme.colors.surface
  ) {
    items.forEach { section ->

      val selected = section == currentSection

      val iconRes = if (selected) section.selectedIcon else section.icon

      BottomNavigationItem(
          icon = { Icon(
              imageResource(id = iconRes),
              modifier = Modifier.width(20.dp).height(20.dp)
          ) },
          selected = selected,
          onSelect = { onSectionSelected(section) },
          alwaysShowLabels = false
      )
    }
  }
}

private enum class HomeSection(
  val icon: Int,
  val selectedIcon: Int
) {
  Home(R.drawable.ic_outlined_home, R.drawable.ic_filled_home),
  Reels(R.drawable.ic_outlined_reels, R.drawable.ic_filled_reels),
  Favorite(R.drawable.ic_outlined_favorite, R.drawable.ic_filled_favorite),
  Profile(R.drawable.ic_outlined_reels, R.drawable.ic_outlined_reels)
}

