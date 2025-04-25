package com.yigitmidye.app.admin.presentation.screen.intro.login


import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yigitmidye.app.admin.HomeActivity
import com.yigitmidye.app.admin.R
import com.yigitmidye.app.admin.presentation.component.button.LoginButton
import com.yigitmidye.app.admin.presentation.component.text_field.EmailTextField
import com.yigitmidye.app.admin.presentation.component.text_field.PasswordTextField
import com.yigitmidye.app.admin.presentation.component.top_app_bar.IntroTopAppBar

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val activity = (context as Activity?)
    val focusManager = LocalFocusManager.current
    val loginUiState by loginViewModel.loginUiState.collectAsState()

    var email by remember { mutableStateOf("".trim()) }
    var password by remember { mutableStateOf("".trim()) }

    loginUiState.errorMessage?.let {
        Toast.makeText(context,it,Toast.LENGTH_LONG).show()
    }

    if (loginUiState.success) {
        val intent = Intent(context,HomeActivity::class.java)
        context.startActivity(intent)
        activity?.finish()
    }

    Scaffold(
        topBar = {
            IntroTopAppBar()
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues = innerPadding)
        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(color = colorResource(R.color.app_const_color)),
                contentAlignment = Alignment.Center
            ) {
                when(loginUiState.loading) {
                    true -> {
                        CircularProgressIndicator(color = colorResource(R.color.white))
                    }
                    else -> {
                        Icon(
                            painter = painterResource(R.drawable.white_logo),
                            contentDescription = null,
                            tint = colorResource(R.color.white),
                            modifier = modifier.size(150.dp)
                        )
                    }
                }
            }
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(color = colorResource(R.color.app_const_color))
                    .clip(shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                    .background(color = colorResource(R.color.white))
            ) {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .background(color = colorResource(R.color.white)),
                    verticalArrangement = Arrangement.spacedBy(30.dp)
                ) {
                    Text(
                        text = "Giriş yap",
                        style = TextStyle(
                            fontSize = 18.sp,
                            color = colorResource(R.color.black),
                            fontWeight = FontWeight.W500
                        ),
                        modifier = modifier.padding(top = 20.dp, start = 20.dp)
                    )
                    EmailTextField(
                        value = email,
                        onValeChange = {email = it},
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Email),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                focusManager.moveFocus(focusDirection = FocusDirection.Next)
                            }
                        )
                    )
                    PasswordTextField(
                        value = password,
                        onValueChange = {password = it},
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Password),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                focusManager.moveFocus(focusDirection = FocusDirection.Down)
                                focusManager.clearFocus()
                            }
                        )
                    )
                    LoginButton(
                        enabled = email.isNotEmpty() && password.isNotEmpty(),
                        onClick = {
                            loginViewModel.userSignInWithEmailAndPassword(email = email, password = password)
                        }
                    )
                }
            }
        }
    }
}
