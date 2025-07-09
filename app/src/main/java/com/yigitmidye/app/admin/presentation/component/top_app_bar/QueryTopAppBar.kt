package com.yigitmidye.app.admin.presentation.component.top_app_bar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import com.yigitmidye.app.admin.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QueryTopAppBar(
    onBackPress : () ->Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "Tarih Sorgu",
                fontSize = 18.sp,
                color = colorResource(R.color.white)
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackPress) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null, tint = colorResource(R.color.white))
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = colorResource(R.color.app_const_color))
    )
}