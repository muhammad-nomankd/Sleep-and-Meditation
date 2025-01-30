package com.durranitech.sleepandmeditation.presentation.ui

import HomeScreen
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun DecisionScreen(navController: NavHostController,) {
    NavHost(navController, startDestination = "HomeScreen") {

        composable("HomeScreen") {
            HomeScreen(navController = navController)
        }
        composable("FavoriteScreen") {
            FavoriteScreen()
        }
        composable("ProfileScreen") {
            ProfileScreen()
        }
        composable("SettingScreen") {
            SettingScreen()
        }
        composable(
            route = "CategoryDetailScreen"
        ) {
            CategoryDetailScreen()
        }
    }
}
