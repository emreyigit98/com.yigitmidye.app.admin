package com.yigitmidye.app.admin.presentation.screen.setting

data class UpdateShopUiState(
    var loading : Boolean = false,
    var errorMessage : String? = null,
    var success : Boolean? = null
)
