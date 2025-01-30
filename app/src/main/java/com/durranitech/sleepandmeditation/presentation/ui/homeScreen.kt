import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.durranitech.sleepandmeditation.R
import com.durranitech.sleepandmeditation.model.SoundItem
import com.durranitech.sleepandmeditation.presentation.ui.BottomNavigationItem
import com.durranitech.sleepandmeditation.presentation.ui.theme.customWhiteforBg
import com.durranitech.sleepandmeditation.presentation.ui.theme.shadeofBlueandGreen
import com.durranitech.sleepandmeditation.viewModel.SoundViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun HomeScreen(navController: NavHostController) {

    rememberSystemUiController().setStatusBarColor(
        shadeofBlueandGreen
    )
    rememberSystemUiController().setNavigationBarColor(
        customWhiteforBg
    )

    val viewModel: SoundViewModel = viewModel()

    val items = listOf(
        SoundItem(0, R.drawable.rain, "Relaxing", R.raw.water_bubbles),
        SoundItem(1, R.drawable.forest, "Forest", R.raw.cricket_insect),
        SoundItem(2, R.drawable.birds, "Birds", R.raw.bird),
        SoundItem(3, R.drawable.thunder, "Thunder", R.raw.rain_thunder),
        SoundItem(4, R.drawable.sun, "Sleep", R.raw.rain_on_roof),
        SoundItem(5, R.drawable.morning, "Morning", R.raw.rain),
        SoundItem(6, R.drawable.yoga, "Yoga", R.raw.violin),
        SoundItem(7, R.drawable.butterfly, "Butterfly", R.raw.saxophone),
        SoundItem(8, R.drawable.images, "Sleep", R.raw.frog),
        SoundItem(9, R.drawable.rain, "Rain", R.raw.rain),
        SoundItem(10, R.drawable.forest, "Forest", R.raw.frog),
        SoundItem(11, R.drawable.birds, "Birds", R.raw.bird),
        SoundItem(12, R.drawable.morning, "Morning", R.raw.river_waves)
    )

    LaunchedEffect(Unit) {
        viewModel.loadSounds(items)
    }
    Scaffold(bottomBar = { BottomNavigationItem(navController) }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            shadeofBlueandGreen, customWhiteforBg
                        )
                    )
                )
        ) {
            Text(
                "Sound Categories",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.align(
                    Alignment.CenterHorizontally
                ),
                color = Color.DarkGray
            )
            LazyColumnWithTwoItemsPerRow(items, navController)
        }

    }
}


@Composable
fun LazyColumnWithTwoItemsPerRow(sounditem: List<SoundItem>, navController: NavController) {

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
                                Image(painterResource(item.iconResId),
                                    contentDescription = "image",
                                    modifier = Modifier
                                        .clickable {
                                            navController.navigate("CategoryDetailScreen") {

                                                launchSingleTop = true
                                                restoreState = true

                                            }
                                        }
                                        .clip(CircleShape)
                                        .size(150.dp),
                                    contentScale = ContentScale.Crop)

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color.Black.copy(0.5f))
                                        .align(Alignment.BottomCenter)
                                ) {
                                    Text(
                                        text = item.title,
                                        color = Color.LightGray,
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