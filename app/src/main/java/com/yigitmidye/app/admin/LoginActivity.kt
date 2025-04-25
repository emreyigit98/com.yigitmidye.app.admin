package com.yigitmidye.app.admin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.yigitmidye.app.admin.presentation.screen.intro.login.LoginScreen
import com.yigitmidye.app.admin.ui.theme.SiparisTakipTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()
            systemUiController.setStatusBarColor(color = colorResource(R.color.app_const_color))
            systemUiController.setNavigationBarColor(color = colorResource(R.color.white))
            SiparisTakipTheme(darkTheme = false) {
                LoginScreen()
            }
        }
    }
}
