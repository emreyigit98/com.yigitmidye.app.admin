package com.yigitmidye.app.admin.presentation.screen.detail

data class UpdateOrderStatusUiState(
    var loading : Boolean = false,
    var errorMessage : String? = null,
    var status : Boolean = false
)