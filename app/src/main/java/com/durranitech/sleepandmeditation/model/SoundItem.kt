package com.durranitech.sleepandmeditation.model

data class SoundItem(
    val id: Int,
    val iconResId: Int,
    val title: String,
    val soundResId: Int,
    var soundVolume: Float = 1.0f
)