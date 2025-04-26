package com.yigitmidye.app.admin.presentation.screen.setting

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yigitmidye.app.admin.R
import com.yigitmidye.app.admin.presentation.component.util.ErrorItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(
    modifier: Modifier = Modifier,
    settingViewModel: SettingViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val activity = (context as? Activity)

    val getShopUiState by settingViewModel.getShopUiState.collectAsState()
    var checked by remember { mutableStateOf(false) }

    getShopUiState.shopStatus?.let {
        checked = it.open
    }

    LaunchedEffect(Unit) {
        settingViewModel.getShopStatus()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Ayarlar",
                        style = TextStyle(
                            fontSize = 18.sp,
                            color = colorResource(R.color.white),
                            fontWeight = FontWeight.W500
                        )
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            activity?.finish()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = colorResource(R.color.white)
                        )
                    }
                },
                actions = {
                    if (getShopUiState.loading) {
                        CircularProgressIndicator(
                            modifier = modifier.size(26.dp),
                            strokeWidth = 2.dp,
                            color = colorResource(R.color.white)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(R.color.app_const_color)
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.white))
                .padding(paddingValues = paddingValues)
        ) {
            getShopUiState.errorMessage?.let { errorMessage ->
                ErrorItem(
                    message = errorMessage,
                    modifier = modifier
                        .fillMaxSize()
                        .background(color = colorResource(R.color.white))
                )
            }
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Online dükkanı Aç/Kapa"
                )
                Switch(
                    checked = checked,
                    onCheckedChange = { value ->
                        checked = value
                        settingViewModel.updateShop(param = checked)
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = colorResource(R.color.app_const_color),
                        uncheckedThumbColor = colorResource(R.color.app_const_color),
                        checkedTrackColor = Color.LightGray,
                        uncheckedTrackColor = Color.LightGray
                    )
                )
            }
        }
    }
}