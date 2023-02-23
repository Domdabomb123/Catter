package com.dschumerth.catter.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink

interface CatsDestination{
    val route: String
    val screenTitle: String
}

object CatSwipe: CatsDestination {
    override val route = "catSwipe"
    override val screenTitle = "Catter"
}

object CatList : CatsDestination {
    override val route = "catsList"
    override val screenTitle = "Your Cats"
}

object CatImage: CatsDestination {
    override val route = "catImage"
    override val screenTitle = "Cat"

    const val catIdArg = "cat_id"
    val routeWithArgs = "$route/{$catIdArg}"
    val arguments = listOf(
        navArgument(catIdArg) { type = NavType.IntType }
    )
    val deepLinks = listOf(
        navDeepLink { uriPattern = "catter://$route/{$catIdArg}" }
    )
}

val catterScreens = listOf(CatSwipe, CatList, CatImage)