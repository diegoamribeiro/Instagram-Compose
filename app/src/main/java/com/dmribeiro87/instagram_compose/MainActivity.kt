package com.dmribeiro87.instagram_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dmribeiro87.instagram_compose.ui.theme.InstagramComposeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InstagramComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    InstagramApp()
                }
            }
        }
    }
}

@Composable
fun InstagramApp() {
    val navHostController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavBar(navHostController) },
        backgroundColor = Color.White
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding))
        InstagramNavGraph(
            navController = navHostController
        )
    }
}

@Composable
fun BottomNavBar(navHostController: NavHostController) {

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val screeList = listOf(
        BottomNavScreens.Home,
        BottomNavScreens.Search,
        BottomNavScreens.Reels,
        BottomNavScreens.Shop,
        BottomNavScreens.Profile,
    )


    BottomNavigation {
        screeList.forEach { screen ->
            BottomNavigationItem(
                selected = currentDestination?.route == screen.route,
                onClick = { navHostController.navigate(screen.route) },
                icon = {
                    if (screen == BottomNavScreens.Profile) {
                        CircularProfileView()
                    } else {
                        Icon(
                            painter = painterResource(id = screen.icon),
                            contentDescription = "nav_icon",
                            tint = if (currentDestination?.route == screen.route) Color.Black else Color.Black
                        )
                    }
                },
                modifier = Modifier.background(color = Color.White),
                selectedContentColor = Color.Red,
                unselectedContentColor = Color.Black,
            )
        }
    }
}

@Composable
fun CircularProfileView() {
    Image(
        painter = painterResource(id = R.drawable.profile_img),
        contentDescription = "content user",
        modifier = Modifier
            .size(25.dp)
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )
}

@Preview(showBackground = true)
@Composable
fun InstagramPreview() {
    InstagramApp()
}