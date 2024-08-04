package com.example.lotterydemo.presentation.navigation

internal enum class MainNavigationScreens(val route: String) {
    LotteryDrawList("lottery_list"),
    LotteryDrawDetails("draw/{drawId}"),
}