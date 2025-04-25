package com.yigitmidye.app.admin.presentation.screen.splash

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.FirebaseAuth
import com.yigitmidye.app.admin.HomeActivity
import com.yigitmidye.app.admin.LoginActivity
import com.yigitmidye.app.admin.R

@Composable
fun AppSplashScreen(
    modifier: Modifier = Modifier,
    splashViewModel: SplashViewModel = viewModel()
) {
    val context = LocalContext.current
    val activity = (context as Activity?)
    val currentUser = FirebaseAuth.getInstance().currentUser
    val nextScreen by splashViewModel.nextScreen.collectAsState()

    if (nextScreen) {
        when {
            currentUser != null -> {
                val intent = Intent(context,HomeActivity::class.java)
                context.startActivity(intent)
                activity?.finish()
            }
            else -> {
                val intent = Intent(context,LoginActivity::class.java)
                context.startActivity(intent)
                activity?.finish()
            }
        }
    }

    Box(
        modifier = modifier.fillMaxSize().background(color = colorResource(R.color.white)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.logo_svg_1),
            contentDescription = null,
            modifier = modifier.size(150.dp)
        )
    }
}