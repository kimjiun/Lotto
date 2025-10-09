package com.jiun.lotto.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.Create
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object WinningNumber : Screen("winning_number", "당첨 번호 확인", Icons.Filled.ConfirmationNumber)
    object NumberGenerator : Screen("number_generator", "번호 생성", Icons.Filled.Create)
}
