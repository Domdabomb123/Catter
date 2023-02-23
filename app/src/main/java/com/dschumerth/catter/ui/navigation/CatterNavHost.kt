package com.dschumerth.catter.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.dschumerth.catter.ui.screens.CatsListScreen
import com.dschumerth.catter.ui.viewmodel.CatterVM
import com.dschumerth.catter.ui.screens.CatsSwipeScreen
import com.dschumerth.catter.ui.screens.CatImageScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class, ExperimentalFoundationApi::class)
@Composable
fun CatterNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: CatterVM
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = CatSwipe.route,
        modifier = modifier
    ) {
        composable(
            route = CatList.route,
            enterTransition = {
                slideInHorizontally(initialOffsetX = { 600 })
            },
            popEnterTransition = {
                slideInHorizontally(initialOffsetX = { 600 })
            }
        ) {
            CatsListScreen(
                viewModel = viewModel,
                onCatClicked = { catId ->
                    navController.navigateToCatImage(catId)
                }
            )
        }
        composable(
            route = CatSwipe.route,
            enterTransition = {
                slideInHorizontally(initialOffsetX = { 600 })
            },
            popEnterTransition = {
                slideInHorizontally(initialOffsetX = { 600 })
            }
        ) {
            CatsSwipeScreen(
                viewModel = viewModel,
                onCatListClicked = {
                    navController.navigateToCatsList()
                }
            )
        }
        composable(
            route = CatImage.routeWithArgs,
            arguments = CatImage.arguments,
            deepLinks = CatImage.deepLinks,
            enterTransition = {
                slideInHorizontally(initialOffsetX = { 600 })
            },
            popEnterTransition = {
                slideInHorizontally(initialOffsetX = { 600 })
            }
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getInt(CatImage.catIdArg)?.let { catId ->
                CatImageScreen(
                    viewModel = viewModel,
                    catId = catId
                )
            }
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.currentBackStackEntry?.id ?: return@navigate
        ) {
            inclusive = true
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }

private fun NavHostController.navigateToCatImage(catId: Int) {
    this.navigateSingleTopTo("${CatImage.route}/$catId")
}

private fun NavHostController.navigateToCatsList() {
    this.navigateSingleTopTo(CatList.route)
}