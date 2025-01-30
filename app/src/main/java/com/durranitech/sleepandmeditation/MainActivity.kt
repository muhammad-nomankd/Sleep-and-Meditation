package com.durranitech.sleepandmeditation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.durranitech.sleepandmeditation.presentation.ui.BottomNavigationItem
import com.durranitech.sleepandmeditation.presentation.ui.DecisionScreen
import com.durranitech.sleepandmeditation.presentation.ui.theme.SleepAndMeditationTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SleepAndMeditationTheme {

                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
                    BottomNavigationItem(navController = navController)
                }) { paddingvalues ->
                    Column(modifier = Modifier.fillMaxSize()) {
                        DecisionScreen(navController = navController)
                    }

                }


            }
        }
    }
}
