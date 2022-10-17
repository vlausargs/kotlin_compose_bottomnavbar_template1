package com.valos.bottomnavigation.mk1
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.valos.bottomnavigation.mk1.ui.component.bottomnav.BottomBar
import com.valos.bottomnavigation.mk1.ui.component.bottomnav.BottomNavGraph
import com.valos.bottomnavigation.mk1.ui.component.bottomnav.screen.BottomBarScreen
import com.valos.bottomnavigation.mk1.ui.theme.CustomNavigationBarTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomNavigationBarTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
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
            }
        }
    }
}