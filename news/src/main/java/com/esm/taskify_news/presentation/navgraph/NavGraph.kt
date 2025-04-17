package com.esm.taskify_news.presentation.navgraph


//import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
//import com.esm.taskify_news.presentation.onboarding.OnBoardingViewModel


@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
//            composable(route = Route.OnBoardingScreen.route) {
//                val viewModel: OnBoardingViewModel = hiltViewModel()
//                OnBoardingScreen(onEvent = viewModel::onEvent)
//            }
        }

        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route
        ) {
            composable(route = Route.NewsNavigatorScreen.route){
//                NewsNavigator()
            }
        }
    }
}