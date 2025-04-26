package com.yigitmidye.app.admin.domain.repo.firestore


import com.google.android.gms.tasks.Task
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query

interface FirebaseFireStoreRepo {
    fun getLocalDateOrders(queryParam : String,startDate : Timestamp,endDate : Timestamp) : Query
    fun getLocalDateOrdersDetail(documentId : String) : Task<DocumentSnapshot>
    fun updateOrderStatus(documentId : String,param : String) : Task<Void>
}