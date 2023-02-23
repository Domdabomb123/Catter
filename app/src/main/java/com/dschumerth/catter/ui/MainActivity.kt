package com.dschumerth.catter.ui

import CatterTheme
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.dschumerth.catter.R
import com.dschumerth.catter.data.Repository
import com.dschumerth.catter.ui.util.CatterViewModelFactory
import com.dschumerth.catter.ui.viewmodel.CatterVM
import com.dschumerth.catter.ui.navigation.*
import com.dschumerth.database.CatDataBaseInstance

class MainActivity : ComponentActivity() {

    private val database by lazy { CatDataBaseInstance.CatRoomDatabase.getDataBase(baseContext) }
    private val catViewModel: CatterVM by viewModels{
        CatterViewModelFactory(Repository(database.appDao()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            CatterApp(catViewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun CatterApp(catViewModel: CatterVM) {
    CatterTheme {
        val navController = rememberAnimatedNavController()
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination
        val currentScreen =
            catterScreens.find { currentDestination?.route?.contains(it.route) ?: false }
                ?: CatList

        Scaffold(
            topBar = {
                CatterAppBar(
                    currentScreen = currentScreen,
                    onBackClicked = { navController.popBackStack() }
                )
            }
        ) { innerPadding ->
            CatterNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding),
                viewModel = catViewModel
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatterAppBar(
    currentScreen: CatsDestination,
    onBackClicked: () -> Unit
) {
    TopAppBar(
        title = { Text(text = currentScreen.screenTitle) },
        navigationIcon = {
            Box(modifier = Modifier.size(56.dp) ) {
                if (currentScreen.route == CatSwipe.route) {
                    Image(
                        painterResource(id = R.mipmap.ic_launcher_foreground),
                        contentDescription = "logo",
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    IconButton(onClick = onBackClicked,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "back")
                    }
                }
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    )
}

@Preview(name = "Top Bar", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CatterAppBarPreview() {
    CatterAppBar(currentScreen = CatList, onBackClicked = {})
}
