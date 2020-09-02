package com.vipulasri.jetinstagram.ui.home

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
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
import androidx.compose.ui.graphics.vector.VectorAsset

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
        HomeSection.Home -> Text(text = "Home")
        HomeSection.Search -> Text(text = "Search")
        HomeSection.Favorite -> Text(text = "Favorite")
        HomeSection.Profile -> Text(text = "Profile")
      }
    }
  }
}

@Composable
private fun BottomBar(
  items: List<HomeSection>,
  currentSection: HomeSection,
  onSectionSelected: (HomeSection) -> Unit,
) {
  BottomNavigation(
      backgroundColor = MaterialTheme.colors.surface
  ) {
    items.forEach { section ->

      val selected = section == currentSection

      val icon = if (selected) section.selectedIcon else section.icon

      BottomNavigationItem(
          icon = { Icon(icon) },
          selected = selected,
          onSelect = { onSectionSelected(section) },
          alwaysShowLabels = false
      )
    }
  }
}

private enum class HomeSection(
  val icon: VectorAsset,
  val selectedIcon: VectorAsset
) {
  Home(Icons.Outlined.Home, Icons.Filled.Home),
  Search(Icons.Outlined.Search, Icons.Filled.Search),
  Favorite(Icons.Outlined.FavoriteBorder, Icons.Filled.Favorite),
  Profile(Icons.Outlined.Person, Icons.Filled.Person)
}

