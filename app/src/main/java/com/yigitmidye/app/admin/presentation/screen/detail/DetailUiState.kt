package com.yigitmidye.app.admin.presentation.screen.detail

import com.yigitmidye.app.admin.domain.model.OrdersModel

data class DetailUiState(
    var loading : Boolean = false,
    val errorMessage : String? = null,
    var order : OrdersModel? = null
)