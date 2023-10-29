package com.dmribeiro87.instagram_compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dmribeiro87.instagram_compose.screens.HomeScreen
import com.dmribeiro87.instagram_compose.screens.NotificationScreen
import com.dmribeiro87.instagram_compose.screens.ProfileScreen
import com.dmribeiro87.instagram_compose.screens.ReelsScreen
import com.dmribeiro87.instagram_compose.screens.SearchScreen

@Composable
fun InstagramNavGraph(
    navController: NavHostController
) {

    NavHost(navController = navController, startDestination = BottomNavRoutes.HOME.route){
        composable(route = BottomNavRoutes.HOME.route){
            HomeScreen()
        }

        composable(route = BottomNavRoutes.SEARCH.route){
            SearchScreen()
        }

        composable(route = BottomNavRoutes.REELS.route){
            ReelsScreen()
        }

        composable(route = BottomNavRoutes.PROFILE.route){
            ProfileScreen()
        }

        composable(route = BottomNavRoutes.SHOP.route){
            NotificationScreen()
        }
    }
}