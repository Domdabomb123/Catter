package com.dschumerth.catter.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.dschumerth.abstractions.CatPhoto
import com.dschumerth.catter.ui.viewmodel.CatterVM

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalFoundationApi
@Composable
fun CatsListScreen(viewModel: CatterVM, onCatClicked: (Int) -> Unit) {
    viewModel.getDataFromRemote()
    val cats by viewModel.catList.observeAsState(null) //= viewModel.getDataFromRemote().collectAsState(initial = null)
    cats?.let { catList ->
        Scaffold(modifier = Modifier.fillMaxSize()) {
            Surface(modifier = Modifier.fillMaxSize().padding(it)) {
                LazyVerticalGrid(columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(catList.size) { index ->
                        CatCard(cat = catList[index], onClicked = { onCatClicked(catList[index].id) })
                    }
                }
            }
        }
    }
}

@Composable
fun CatCard(cat: CatPhoto, onClicked: () -> Unit) {
    Card(
        modifier = Modifier
            .wrapContentWidth()
            .height(IntrinsicSize.Max)
            .padding(top = 8.dp, bottom = 4.dp, start = 16.dp, end = 16.dp)
            .clickable(onClick = onClicked)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            cat.image?.let { Image(bitmap = it.asImageBitmap(), contentDescription = null) }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(title: String, icon: ImageVector, onIconClicked: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            Icon(
                icon,
                "back",
                Modifier
                    .padding(horizontal = 12.dp)
                    .clickable(onClick = { onIconClicked.invoke() })
            )
        },
        title = { Text(text = title) }
    )
}