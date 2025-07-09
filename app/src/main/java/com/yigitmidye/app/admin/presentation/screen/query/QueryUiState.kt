package com.yigitmidye.app.admin.presentation.screen.query

import com.yigitmidye.app.admin.domain.model.OrdersModel

data class QueryUiState(
    var loading : Boolean = false,
    var errorMessage : String? = null,
    var successful : List<OrdersModel>? = null
)