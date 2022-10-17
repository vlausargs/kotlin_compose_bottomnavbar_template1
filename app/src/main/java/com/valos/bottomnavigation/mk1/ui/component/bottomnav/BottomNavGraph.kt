package com.valos.bottomnavigation.mk1.ui.component.bottomnav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.valos.bottomnavigation.mk1.ui.component.bottomnav.screen.BottomBarScreen
import com.valos.bottomnavigation.mk1.ui.component.bottomnav.screen.HomeScreen
import com.valos.bottomnavigation.mk1.ui.component.bottomnav.screen.ProfileScreen
import com.valos.bottomnavigation.mk1.ui.component.bottomnav.screen.ReportScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = BottomBarScreen.Home.route ){
        composable(route = BottomBarScreen.Home.route){
            HomeScreen()
        }
        composable(route = BottomBarScreen.Report.route){
            ReportScreen()
        }
        composable(route = BottomBarScreen.Profile.route){
            ProfileScreen()
        }

    }
}