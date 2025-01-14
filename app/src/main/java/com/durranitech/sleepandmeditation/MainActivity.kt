package com.durranitech.sleepandmeditation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.durranitech.presentation.ui.BottomNavigationItem
import com.durranitech.presentation.ui.DecisionScreen
import com.durranitech.presentation.ui.theme.SleepAndMeditationTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SleepAndMeditationTheme {

                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
                    BottomNavigationItem(navController)
                }) { paddingvalues ->
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingvalues)) { }
                    DecisionScreen(navController)
                }


            }
        }
    }
}
