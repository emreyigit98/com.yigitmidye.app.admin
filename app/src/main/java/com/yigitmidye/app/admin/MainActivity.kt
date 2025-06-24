package com.yigitmidye.app.admin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.res.colorResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.yigitmidye.app.admin.presentation.screen.splash.AppSplashScreen
import com.yigitmidye.app.admin.ui.theme.SiparisTakipTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            installSplashScreen()
            val systemUiController = rememberSystemUiController()
            systemUiController.setStatusBarColor(color = colorResource(R.color.white))
            systemUiController.setNavigationBarColor(color = colorResource(R.color.white))
            SiparisTakipTheme(darkTheme = false) {
                AppSplashScreen()
            }
        }
    }
}
