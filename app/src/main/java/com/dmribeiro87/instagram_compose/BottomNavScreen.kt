package com.dmribeiro87.instagram_compose

sealed class BottomNavScreens(
    val title: String,
    val icon: Int = R.drawable.ic_home,
    val route: String
) {

    object Home: BottomNavScreens(title = "home", icon = R.drawable.ic_home, route = BottomNavRoutes.HOME.route)
    object Search: BottomNavScreens(title = "search", icon = R.drawable.ic_search, route = BottomNavRoutes.SEARCH.route)
    object Reels: BottomNavScreens(title = "reels", icon = R.drawable.ic_reels, route = BottomNavRoutes.REELS.route)
    object Shop: BottomNavScreens(title = "notifications", icon = R.drawable.ic_shop, route = BottomNavRoutes.SHOP.route)
    object Profile: BottomNavScreens(title = "profile", route = BottomNavRoutes.PROFILE.route)

}

enum class BottomNavRoutes(val route: String){
    HOME("home"),
    SEARCH("search"),
    REELS("reels"),
    SHOP("shop"),
    PROFILE("profile")
}