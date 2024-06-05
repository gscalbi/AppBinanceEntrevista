package com.fatal.appbinanceentrevista.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fatal.appbinanceentrevista.presentation.screens.tickerDetails.TickerDetailScreen
import com.fatal.appbinanceentrevista.presentation.screens.tickerList.TickerListScreen

@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "tickerList"
    ) {
        composable("tickerList") {
            TickerListScreen(onTickerClick = { symbol ->
                navController.navigate("tickerDetail/$symbol")
            })
        }

        composable("tickerDetail/{symbol}") { backStackEntry ->
            val symbol = backStackEntry.arguments?.getString("symbol") ?: return@composable
            TickerDetailScreen(symbol)
        }


    }


}