package com.durranitech.sleepandmeditation.data

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationBarItem(val title:String, val selectedIcon: ImageVector, val nonSelectedIcon: ImageVector, val route:String, val news: Boolean, val badgeCount: Int? = null )
