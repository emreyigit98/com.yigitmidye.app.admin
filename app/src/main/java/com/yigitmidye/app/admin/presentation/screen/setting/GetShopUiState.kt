package com.yigitmidye.app.admin.presentation.screen.setting

import com.yigitmidye.app.admin.domain.model.ShopModel

data class GetShopUiState(
    var loading : Boolean = false,
    var errorMessage : String? = null,
    var shopStatus : ShopModel? = null
)
