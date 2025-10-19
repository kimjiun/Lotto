package com.jiun.lotto.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home : Screen("home", "홈", Icons.Filled.Home)
    object History : Screen("history", "회차별 당첨번호", Icons.Filled.FormatListNumbered)
    object NumberGenerator : Screen("number_generator", "번호 생성", Icons.Filled.Create)
}
