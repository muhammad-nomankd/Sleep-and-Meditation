package com.durranitech.sleepandmeditation.presentation.ui

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.durranitech.sleepandmeditation.R
import com.durranitech.sleepandmeditation.model.SoundItem
import com.durranitech.sleepandmeditation.presentation.ui.theme.customWhiteforBg
import com.durranitech.sleepandmeditation.presentation.ui.theme.shadeofBlueandGreen
import com.durranitech.sleepandmeditation.viewModel.SoundViewModel

@Composable
fun CategoryDetailScreen() {

    val viewModel = SoundViewModel()
    val fetchedSoundItemsFromVM = viewModel.soundItems
    val context = LocalContext.current

    if (fetchedSoundItemsFromVM.isEmpty()) {
        viewModel.loadSounds(
            listOf(
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
                SoundItem(12, R.drawable.morning, "Morning", R.raw.river_waves),
            )
        )
        LazyColumn(
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            shadeofBlueandGreen, customWhiteforBg
                        )
                    )
                )
                .fillMaxSize()
                .padding(start = 32.dp, end = 32.dp, top = 48.dp, bottom = 96.dp)
        ) {
            items(fetchedSoundItemsFromVM) { items ->
                SoundItemRow(viewModel, items, context)

            }
        }
    }

}

@Composable
fun SoundItemRow(viewModel: SoundViewModel, soundItem: SoundItem, context: Context) {
    remember { mutableStateOf(false) }
    val volumes by viewModel.volumes.collectAsState()
    val currentVolume = volumes[soundItem.id] ?: soundItem.soundVolume


    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable {

                    viewModel.toggleSound(context, soundItem.id)
                }, horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.Bottom
        ) {
            Image(
                painterResource(soundItem.iconResId),
                contentDescription = "sound Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(48.dp)
                    .clip(shape = CircleShape)
            )

            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.padding(8.dp)
            ) {
                Text(soundItem.title, modifier = Modifier.padding(start = 8.dp, bottom = 4.dp))
                Slider(
                    value = currentVolume, onValueChange = { newVolume ->
                        viewModel.adjustVolume(soundItem.id, newVolume)
                    }, valueRange = 0f..1f, modifier = Modifier.height(8.dp)
                )
            }


        }


    }

}