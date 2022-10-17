package com.valos.bottomnavigation.mk1.ui.component.bottomnav

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.valos.bottomnavigation.mk1.ui.component.bottomnav.screen.BottomBarScreen
import com.valos.bottomnavigation.mk1.ui.theme.Purple500

@Composable
fun BottomNavExample() {
    val navController = rememberNavController()
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Report,
        BottomBarScreen.Profile
    )
    Scaffold(
        bottomBar = { BottomBar(navHostController = navController,screens=screens) }
    ) {
        BottomNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(navHostController: NavHostController , screens: List<BottomBarScreen>) {

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination


    Row(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 8.dp, bottom = 8.dp)
            .shadow(elevation = 10.dp, clip = false, shape = RoundedCornerShape(10))
            .background(MaterialTheme.colors.background,shape=RoundedCornerShape(10))
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navHostController = navHostController
            )
        }
    }


}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navHostController: NavHostController
) {
    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
    val background = if (selected) MaterialTheme.colors.primary.copy(alpha = 0.6f) else Color.Transparent
    val contentColor = MaterialTheme.colors.onSecondary

    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .graphicsLayer(
                translationY = if (selected)animateFloatAsState(targetValue = -40f).value else 0f
            )
            .padding(start = 4.dp, end = 4.dp)
            .width(70.dp)
            .height(55.dp)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null,
                onClick = {
                    navHostController.navigate(screen.route) {
                        popUpTo(navHostController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                }
            )
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp)
                    .clip(CircleShape)
                    .background(background),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    modifier = Modifier.padding(4.dp),
                    painter = painterResource(id = if (selected) screen.icon_focused else screen.icon),
                    contentDescription = "icon",
                    tint = contentColor
                )

            }
            AnimatedVisibility(visible = selected) {
                Column(
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(text = screen.title, color = contentColor)

                }
            }
        }


    }

}

@Preview
@Composable
fun BottomNavPreview() {
    BottomNavExample()
}