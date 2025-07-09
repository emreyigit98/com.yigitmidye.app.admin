package com.yigitmidye.app.admin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.res.colorResource
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.yigitmidye.app.admin.presentation.screen.query.QueryScreen
import com.yigitmidye.app.admin.ui.theme.SiparisTakipTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QueryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()
            systemUiController.setStatusBarColor(color = colorResource(R.color.app_const_color))
            systemUiController.setNavigationBarColor(color = colorResource(R.color.white))
            SiparisTakipTheme(darkTheme = false) {
                QueryScreen()
            }
        }
    }
}
