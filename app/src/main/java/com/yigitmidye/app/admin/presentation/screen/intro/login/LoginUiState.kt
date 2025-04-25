package com.yigitmidye.app.admin.presentation.screen.intro.login

data class LoginUiState(
    var loading : Boolean = false,
    var errorMessage : String? = null,
    var success : Boolean = false
)