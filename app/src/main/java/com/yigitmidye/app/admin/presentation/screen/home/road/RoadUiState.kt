package com.yigitmidye.app.admin.presentation.screen.home.road

import com.yigitmidye.app.admin.domain.model.OrdersModel

data class RoadUiState(
    var loading : Boolean = false,
    var errorMessage : String? = null,
    var roadList : List<OrdersModel>? = null
)