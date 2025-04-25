package com.yigitmidye.app.admin.domain.model

data class OrderModel(
    val documentId : String = "",
    val amount : Int = 0,
    val img : String = "",
    val productName : String = "",
    val productPrice : Double = 0.0,
    val totalPrice : Double = 0.0
)