package com.valos.bottomnavigation.mk1.ui.component.bottomnav.screen

import com.valos.bottomnavigation.mk1.R

sealed class BottomBarScreen(
    val route:String,
    val title:String,
    val icon:Int,
    val icon_focused:Int,
){
    object  Home : BottomBarScreen(
        route = "home",
        title = "home",
        icon = R.drawable.ic_bottom_home,
        icon_focused = R.drawable.ic_bottom_home_focused
    )
    object  Report : BottomBarScreen(
        route = "report",
        title = "report",
        icon = R.drawable.ic_bottom_home,
        icon_focused = R.drawable.ic_bottom_home_focused
    )
    object  Profile : BottomBarScreen(
        route = "profile",
        title = "profile",
        icon = R.drawable.ic_bottom_home,
        icon_focused = R.drawable.ic_bottom_home_focused
    )
}
