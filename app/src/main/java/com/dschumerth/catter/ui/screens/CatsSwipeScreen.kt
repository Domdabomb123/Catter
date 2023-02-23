package com.dschumerth.catter.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dschumerth.abstractions.CatPhoto
import com.dschumerth.catter.R
import com.dschumerth.catter.ui.viewmodel.CatterVM

@Composable
fun CatsSwipeScreen(
    viewModel: CatterVM,
    onCatListClicked: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Max)
                .width(IntrinsicSize.Max)
                .weight(1f)
        ) {
            val img by viewModel.catResponse.observeAsState(null)
            img?.let {
                Image(
                    bitmap = it.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Max)
                .width(IntrinsicSize.Max)
                .padding(bottom = 10.dp)
        ) {
            Button(
                onClick = { viewModel.getImage() },
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                modifier = Modifier.padding(end = 10.dp)
            ) {
                Text(text = "Next", modifier = Modifier.padding(end = 6.dp), color = Color.White)
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_pets_24),
                    contentDescription = "Cat paw",
                    modifier = Modifier.size(20.dp)
                )
            }
            Button(
                onClick = {
                    viewModel.insert(
                        CatPhoto(viewModel.catResponse.value)
                    )
                    viewModel.getImage()
                },
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
            ) {
                Text(text = "Save", modifier = Modifier.padding(end = 6.dp), color = Color.White)
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_add_24),
                    contentDescription = "Plus",
                    modifier = Modifier.size(20.dp)
                )
            }
            Button(
                onClick = { onCatListClicked() },
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                modifier = Modifier.padding(start = 10.dp)
            ) {
                Text(
                    text = "Cats",
                    modifier = Modifier.padding(end = 6.dp),
                    color = Color.White
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_dashboard_24dp),
                    contentDescription = "List",
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}