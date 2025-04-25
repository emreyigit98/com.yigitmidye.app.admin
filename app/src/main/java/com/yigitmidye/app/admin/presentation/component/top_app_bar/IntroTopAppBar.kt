package com.yigitmidye.app.admin.presentation.component.top_app_bar

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yigitmidye.app.admin.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IntroTopAppBar(modifier: Modifier = Modifier) {
    TopAppBar(
        title = {
            Text(
                text = "firebaseMyAdmin",
                style = TextStyle(
                    fontSize = 18.sp,
                    color = colorResource(R.color.white),
                    fontWeight = FontWeight.W500
                )
            )
        },
        navigationIcon = {
            Icon(
                painter = painterResource(R.drawable.firebase_logo), contentDescription = null,
                modifier = modifier.size(36.dp).padding(start = 10.dp),
                tint = colorResource(R.color.white)
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(R.color.app_const_color)
        )
    )
}