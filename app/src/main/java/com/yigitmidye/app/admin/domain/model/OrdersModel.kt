package com.yigitmidye.app.admin.domain.model

import com.google.firebase.Timestamp
import com.google.j2objc.annotations.Property

data class OrdersModel(
    var documentId : String = "",
    @Property("userId")
    val userId : String = "",
    @Property("address")
    val address : String = "",
    @Property("orderNote")
    val orderNote : String = "",
    @Property("orderStatus")
    val orderStatus : String = "",
    @Property("orders")
    val orders : List<OrderModel> = emptyList(),
    @Property("paymentType")
    val paymentType : String = "",
    @Property("phone")
    val phone : String = "",
    @Property("timestamp")
    val timestamp : Timestamp = Timestamp.now(),
    @Property("totalPrice")
    val totalPrice : Double = 0.0
)