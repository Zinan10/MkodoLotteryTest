package com.example.lotterydemo.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.lotterydemo.ui.theme.LotteryDemoTheme
import com.example.lotterydemo.presentation.navigation.MainNavigation

@Composable
internal fun Application() {
    LotteryDemoTheme {
        val navController = rememberNavController()
        MainNavigation(navController)
    }
}
