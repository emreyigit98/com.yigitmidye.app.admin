package com.yigitmidye.app.admin.presentation.screen.home.approved

import com.yigitmidye.app.admin.domain.model.OrdersModel

data class ApprovedUiState(
    var loading : Boolean = false,
    var errorMessage : String? = null,
    var approvedList : List<OrdersModel>? = null
)