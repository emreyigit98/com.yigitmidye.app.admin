package com.yigitmidye.app.admin.presentation.screen.home.pending

import com.yigitmidye.app.admin.domain.model.OrdersModel

data class PendingUiState(
    var loading : Boolean = false,
    var errorMessage : String? = null,
    var pendingList : List<OrdersModel>? = null
)