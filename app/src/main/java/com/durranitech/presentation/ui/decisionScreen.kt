package com.durranitech.presentation.ui

import HomeScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun DecisionScreen(navController: NavHostController) {
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
    }
}
