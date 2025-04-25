package com.yigitmidye.app.admin.presentation.screen.home.delivery

import com.yigitmidye.app.admin.domain.model.OrdersModel

data class DeliveryUiState(
    var loading : Boolean = false,
    var errorMessage : String? = null,
    var deliveryList : List<OrdersModel>? = null
)