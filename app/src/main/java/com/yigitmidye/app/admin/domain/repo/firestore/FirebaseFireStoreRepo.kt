package com.yigitmidye.app.admin.domain.repo.firestore


import com.google.android.gms.tasks.Task
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot

interface FirebaseFireStoreRepo {
    fun getLocalDateOrders(queryParam : String,startDate : Timestamp,endDate : Timestamp) : Query
    fun getQueryDateOrders(startDate: Timestamp,endDate: Timestamp) : Task<QuerySnapshot>
    fun getLocalDateOrdersDetail(documentId : String) : Task<DocumentSnapshot>
    fun updateOrderStatus(documentId : String,param : String) : Task<Void>
    fun updateShop(param : Boolean) : Task<Void>
    fun getShopStatus() : Task<DocumentSnapshot>
}