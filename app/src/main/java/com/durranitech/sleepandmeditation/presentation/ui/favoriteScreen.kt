package com.durranitech.sleepandmeditation.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.sp
import com.durranitech.sleepandmeditation.presentation.ui.theme.customWhiteforBg
import com.durranitech.sleepandmeditation.presentation.ui.theme.shadeofBlueandGreen

@Composable
fun FavoriteScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    listOf(
                        shadeofBlueandGreen, customWhiteforBg
                    )
                )
            ), contentAlignment = Alignment.Center
    ) {
        Text(
            "This is Favorite Screen",
            fontSize = 32.sp,

            )
    }
}