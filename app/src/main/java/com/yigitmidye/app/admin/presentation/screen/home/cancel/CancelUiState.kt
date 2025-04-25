package com.yigitmidye.app.admin.presentation.screen.home.cancel

import com.yigitmidye.app.admin.domain.model.OrdersModel

data class CancelUiState(
    var loading : Boolean = false,
    var errorMessage : String? = null,
    var cancelList : List<OrdersModel>? = null
)