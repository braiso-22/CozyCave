package com.braiso_22.cozycave.ui.common

import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass

fun WindowSizeClass.isVertical(): Boolean = widthSizeClass == WindowWidthSizeClass.Compact
        || heightSizeClass == WindowHeightSizeClass.Expanded
