package com.vipulasri.jetinstagram.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Box
import androidx.compose.foundation.ContentGravity
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.contentColor
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vipulasri.jetinstagram.R
import com.vipulasri.jetinstagram.model.currentUser
import com.vipulasri.jetinstagram.ui.HomeSection.Add
import com.vipulasri.jetinstagram.ui.HomeSection.Favorite
import com.vipulasri.jetinstagram.ui.HomeSection.Home
import com.vipulasri.jetinstagram.ui.HomeSection.Profile
import com.vipulasri.jetinstagram.ui.HomeSection.Reels
import com.vipulasri.jetinstagram.ui.components.bottomBarHeight
import com.vipulasri.jetinstagram.ui.components.icon
import com.vipulasri.jetinstagram.ui.home.Home
import com.vipulasri.jetinstagram.ui.reels.Reels
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun MainScreen() {
  val (currentSection, setCurrentSection) = savedInstanceState { Home }
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
    Crossfade(
        modifier = modifier,
        current = currentSection) { section ->
        when (section) {
            Home -> Home()
            Reels -> Reels()
            Add -> Content(title = "Add Post options")
            Favorite -> Content(title = "Favorite")
            Profile -> Content(title = "Profile")
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
        style = MaterialTheme.typography.h5
    )
  }
}

@Composable
private fun BottomBar(
  items: List<HomeSection>,
  currentSection: HomeSection,
  onSectionSelected: (HomeSection) -> Unit,
) {
  BottomNavigation(
      modifier = Modifier.height(bottomBarHeight),
      backgroundColor = MaterialTheme.colors.background,
      contentColor = contentColor()
  ) {
    items.forEach { section ->

      val selected = section == currentSection

      val iconRes = if (selected) section.selectedIcon else section.icon

      BottomNavigationItem(
          icon = {

            if (section == Profile) {
              BottomBarProfile(selected)
            } else {
              Icon(
                  imageResource(id = iconRes),
                  modifier = Modifier.icon()
              )
            }

          },
          selected = selected,
          onSelect = { onSectionSelected(section) },
          alwaysShowLabels = false
      )
    }
  }
}

@Composable
private fun BottomBarProfile(isSelected: Boolean) {
  val shape = CircleShape

  val borderModifier = if (isSelected) {
    Modifier
        .border(
            color = Color.LightGray,
            width = 1.dp,
            shape = shape
        )
  } else Modifier

  val padding = if (isSelected) 3.dp else 0.dp

  Box(
      modifier = borderModifier
  ) {
    Box(
        modifier = Modifier.icon()
            .padding(padding)
            .background(color = Color.LightGray, shape = shape)
            .clip(shape)
    ) {
      CoilImage(
          data = currentUser.image,
          contentScale = ContentScale.Crop,
          modifier = Modifier.fillMaxSize()
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
  Add(R.drawable.ic_outlined_add, R.drawable.ic_outlined_add),
  Favorite(R.drawable.ic_outlined_favorite, R.drawable.ic_filled_favorite),
  Profile(R.drawable.ic_outlined_reels, R.drawable.ic_outlined_reels)
}

