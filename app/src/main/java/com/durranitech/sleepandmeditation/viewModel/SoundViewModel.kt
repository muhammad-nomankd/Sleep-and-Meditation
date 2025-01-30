package com.durranitech.sleepandmeditation.viewModel

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.media.MediaPlayer
import android.util.Log
import androidx.lifecycle.ViewModel
import com.durranitech.sleepandmeditation.model.SoundItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.io.IOException

class SoundViewModel : ViewModel() {

    private val _soundItems = mutableListOf<SoundItem>()
    val soundItems: List<SoundItem> get() = _soundItems

    private val _volumes = MutableStateFlow(mutableMapOf<Int, Float>())
    val volumes = _volumes.asStateFlow()

    private val mediaPlayers = mutableMapOf<Int, MediaPlayer>()

    fun loadSounds(soundList: List<SoundItem>) {
        _soundItems.clear()
        _soundItems.addAll(soundList)
    }

    fun toggleSound(context: Context, soundId: Int) {

        if (mediaPlayers.containsKey(soundId)) {
            stopSound(soundId)
        } else {
            startSound(
                context,
                soundItem = soundItems.find { it.id == soundId } ?: return
            )
        }

    }

    fun startSound(context: Context, soundItem: SoundItem) {
        if (mediaPlayers.containsKey(soundItem.id)) return // Prevent duplicate play

        val mediaPlayer = MediaPlayer()
        try {
            val afd: AssetFileDescriptor = context.resources.openRawResourceFd(soundItem.soundResId) ?: return
            mediaPlayer.setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
            afd.close()

            mediaPlayer.prepare()
            mediaPlayer.isLooping = true
            mediaPlayer.setVolume(soundItem.soundVolume, soundItem.soundVolume)
            mediaPlayer.start()

            mediaPlayers[soundItem.id] = mediaPlayer
        } catch (e: IOException) {
            Log.e("SoundViewModel", "Failed to start sound: ${soundItem.soundResId}", e)
        }
    }

    fun stopSound(soundId: Int) {
        mediaPlayers[soundId]?.apply {
            stop()
            release()
        }

        mediaPlayers.remove(soundId)
    }

    fun adjustVolume(soundId: Int, volume: Float) {
        mediaPlayers[soundId]?.setVolume(volume, volume) // Update media player volume
        _volumes.update { it.toMutableMap().apply { this[soundId] = volume } } // Update state
    }

    override fun onCleared() {
        super.onCleared()
        mediaPlayers.values.forEach { it.release() }
        mediaPlayers.clear()
    }


}