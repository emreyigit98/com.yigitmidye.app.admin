package com.yigitmidye.app.admin.presentation.component.top_app_bar


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.yigitmidye.app.admin.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(
    modifier: Modifier = Modifier,
    text : String,
    settingClick : () -> Unit,
    queryClick : () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = text,
                style = TextStyle(
                    fontSize = 18.sp,
                    color = colorResource(R.color.white),
                    fontWeight = FontWeight.W500
                )
            )
        },
        actions = {
            IconButton(
                onClick = { settingClick() }
            ) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = null,
                    tint = colorResource(R.color.white)
                )
            }
            IconButton(
                onClick = { queryClick() }
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null,
                    tint = colorResource(R.color.white)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(R.color.app_const_color)
        )
    )
}