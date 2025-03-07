package com.redvelvet.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing(
    val spacing0: Dp = 0.dp,
    val spacing2: Dp = 2.dp,
    val spacing4: Dp = 4.dp,
    val spacing8: Dp = 8.dp,
    val spacing12: Dp = 12.dp,
    val spacing16: Dp = 16.dp,
    val spacing20: Dp = 20.dp,
    val spacing24: Dp = 24.dp,
    val spacing28: Dp = 28.dp,
    val spacing32: Dp = 32.dp,
    val spacing40: Dp = 40.dp,
    val spacing44: Dp = 44.dp,
    val spacing48: Dp = 48.dp,
    val spacing56: Dp = 56.dp,
    val spacing58: Dp = 58.dp,
    val spacing64: Dp = 64.dp,
    val spacing72: Dp = 72.dp,
    val spacing80: Dp = 80.dp,
    val spacing285: Dp = 285.dp,
    val spacing127: Dp = 127.dp,
    val spacing100: Dp = 100.dp,
)

val LocalSpacing = compositionLocalOf { Spacing() }

val MaterialTheme.spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current