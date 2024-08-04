package com.example.lotterydemo.presentation.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lotterydemo.presentation.screens.drawdetails.DrawDetailsScreen
import com.example.lotterydemo.presentation.screens.drawdetails.DrawDetailsViewModel
import com.example.lotterydemo.presentation.screens.drawdetails.personalDrawDetailsViewModel
import com.example.lotterydemo.presentation.screens.drawlist.LotteryDrawListScreen

@Composable
fun MainNavigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = MainNavigationScreens.LotteryDrawList.route,
        exitTransition = { NavExitTransitionDefault },
        enterTransition = { NavEnterTransitionHorizontalLeft },
        popExitTransition = { NavExitTransitionDefault },
        popEnterTransition = { NavEnterTransitionHorizontalRight }
    ) {
        addLotteryDrawListScreen(navController)
        addLotteryDrawDetailScreen(navController)
    }

    LaunchedEffect(Unit) {
        navController.currentBackStackEntryFlow.collect { entry ->
            val route = entry.destination.route.orEmpty()
            Log.d("Navigation", "Route : $route")
        }
    }

}

private fun NavGraphBuilder.addLotteryDrawListScreen(navController: NavHostController) {
    composable(route = MainNavigationScreens.LotteryDrawList.route) {
        LotteryDrawListScreen(
            viewModel = hiltViewModel(),
            onDrawItemClicked = { drawId ->
                navController.navigate(
                    MainNavigationScreens.LotteryDrawDetails.route.replace(
                        "{drawId}",
                        drawId
                    )
                )
            }
        )
    }
}

private fun NavGraphBuilder.addLotteryDrawDetailScreen(navController: NavHostController) {
    composable(route = MainNavigationScreens.LotteryDrawDetails.route) { backStackEntry ->
        val drawId = backStackEntry.arguments?.getString("drawId").orEmpty()
        DrawDetailsScreen(
            viewModel = personalDrawDetailsViewModel(drawId = drawId)
        )
    }
}

