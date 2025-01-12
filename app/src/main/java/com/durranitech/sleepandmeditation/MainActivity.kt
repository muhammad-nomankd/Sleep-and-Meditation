package com.durranitech.sleepandmeditation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.durranitech.sleepandmeditation.data.model.SoundItem
import com.durranitech.sleepandmeditation.ui.theme.SleepAndMeditationTheme
import com.durranitech.sleepandmeditation.ui.theme.customWhiteforBg
import com.durranitech.sleepandmeditation.ui.theme.shadeofBlueandGreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SleepAndMeditationTheme {

                rememberSystemUiController().setStatusBarColor(
                    shadeofBlueandGreen
                )
                rememberSystemUiController().setNavigationBarColor(
                    customWhiteforBg
                )

                val items = listOf<SoundItem>(
                    SoundItem(R.drawable.rain, "Relaxing", 0),
                    SoundItem(R.drawable.forest, "Forest", 1),
                    SoundItem(R.drawable.birds, "Birds", 2),
                    SoundItem(R.drawable.thunder, "Thunder", 3),
                    SoundItem(R.drawable.drop, "Sleep", 3),
                )
                Scaffold { padding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding)
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        shadeofBlueandGreen, customWhiteforBg
                                    ), start = Offset(0f, 0f), end = Offset(0f, 1000f)
                                )
                            )
                    ) {
                        Spacer(Modifier.height(18.dp))
                        Text(
                            "Sound Categories",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.align(
                                Alignment.CenterHorizontally
                            ),
                            color = Color.Gray
                        )
                        LazyColumnWithTwoItemsPerRow(items)
                    }

                }
            }
        }
    }
}


@Composable
fun LazyColumnWithTwoItemsPerRow(sounditem: List<SoundItem>) {

    LazyColumn {
        items(sounditem.chunked(2)) { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
            ) {
                rowItems.forEach { item ->
                    Column(modifier = Modifier.padding(12.dp)) {
                        Card(
                            elevation = CardDefaults.cardElevation(16.dp),
                            shape = CircleShape,
                            modifier = Modifier.size(150.dp)
                        ) {
                            Box {
                                Image(
                                    painterResource(item.img),
                                    contentDescription = "image",
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .size(150.dp),
                                    contentScale = ContentScale.Crop
                                )

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color.Black.copy(0.5f))
                                        .align(Alignment.BottomCenter)
                                ){
                                    Text(
                                        text = item.title,
                                        color = Color.Gray,
                                        fontWeight = FontWeight.Normal,
                                        modifier = Modifier
                                            .padding(8.dp, bottom = 12.dp)
                                            .align(Alignment.Center)
                                    )
                                }

                            }


                        }


                    }
                    if (rowItems.size < 2) {
                        Spacer(modifier = Modifier.width(150.dp))
                    }


                }
            }


        }
    }

}