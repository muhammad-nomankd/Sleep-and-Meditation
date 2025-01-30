package com.durranitech.sleepandmeditation.presentation.ui

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.durranitech.sleepandmeditation.data.BottomNavigationBarItem
import com.durranitech.sleepandmeditation.presentation.ui.theme.customWhiteforBg
import com.durranitech.sleepandmeditation.presentation.ui.theme.shadeofBlueandGreen

@Composable
fun BottomNavigationItem(navController: NavHostController) {
    val bottomItemsList = listOf(
        BottomNavigationBarItem(
            title = "Home",
            selectedIcon = Icons.Default.Home,
            nonSelectedIcon = Icons.Outlined.Home,
            route = "HomeScreen",
            news = false
        ),
        BottomNavigationBarItem(
            title = "Favorite",
            selectedIcon = Icons.Default.Favorite,
            nonSelectedIcon = Icons.Outlined.FavoriteBorder,
            route = "FavoriteScreen",
            news = false
        ),
        BottomNavigationBarItem(
            title = "Profile",
            selectedIcon = Icons.Default.Person,
            nonSelectedIcon = Icons.Outlined.Person,
            route = "ProfileScreen",
            news = false
        ),
        BottomNavigationBarItem(
            title = "Setting",
            selectedIcon = Icons.Default.Settings,
            nonSelectedIcon = Icons.Outlined.Settings,
            route = "SettingScreen",
            news = true
        )
    )

    var currentlySelectedItem by remember { mutableStateOf(0) }

    NavigationBar(modifier = Modifier.height(90.dp), containerColor = customWhiteforBg) {
        val currentDestination = navController.currentBackStackEntryAsState().value?.destination

        bottomItemsList.forEachIndexed { index, item ->
            val interactionSource = remember { MutableInteractionSource() }

            NavigationBarItem(
                selected = currentDestination != null && currentDestination.route == item.route,
                icon = {
                    BadgedBox(badge = {
                        if (item.badgeCount != null) {
                            Badge { Text(item.badgeCount.toString()) }
                        } else if (item.news) {
                            Badge()
                        }
                    }) {
                        Icon(
                            imageVector = if (index == currentlySelectedItem) {
                                item.selectedIcon
                            } else item.nonSelectedIcon,
                            contentDescription = item.title
                        )
                    }
                },
                modifier = Modifier.padding(8.dp),
                enabled = true,
                label = { Text(item.title, fontSize = 16.sp) },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.DarkGray,
                    unselectedIconColor = Color.Gray,
                    indicatorColor = shadeofBlueandGreen,
                    selectedTextColor = Color.DarkGray,
                    unselectedTextColor = Color.DarkGray
                ),
                onClick = {
                    currentlySelectedItem = index
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                interactionSource = interactionSource
            )
        }
    }
}
