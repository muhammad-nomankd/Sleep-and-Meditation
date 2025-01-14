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
import androidx.navigation.NavHostController
import com.durranitech.presentation.ui.BottomNavigationItem
import com.durranitech.presentation.ui.theme.customWhiteforBg
import com.durranitech.presentation.ui.theme.shadeofBlueandGreen
import com.durranitech.sleepandmeditation.R
import com.durranitech.sleepandmeditation.data.BottomNavigationBarItem
import com.durranitech.sleepandmeditation.data.model.SoundItem
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun HomeScreen(navController: NavHostController) {

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
        SoundItem(R.drawable.sun, "Sleep", 4),
        SoundItem(R.drawable.morning, "Morning", 5),
        SoundItem(R.drawable.yoga, "Yoga", 6),
        SoundItem(R.drawable.butterfly, "Butterfly", 7),
        SoundItem(R.drawable.images, "Sleep", 8),
        SoundItem(R.drawable.rain, "Rain", 9),
        SoundItem(R.drawable.forest, "Forest", 10),
        SoundItem(R.drawable.birds, "Birds", 11),
        SoundItem(R.drawable.morning, "Morning", 12),
    )
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
            LazyColumnWithTwoItemsPerRow(items)
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