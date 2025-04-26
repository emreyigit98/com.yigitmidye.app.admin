package com.yigitmidye.app.admin.data.repo.firestore


import com.google.android.gms.tasks.Task
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.yigitmidye.app.admin.domain.repo.firestore.FirebaseFireStoreRepo
import javax.inject.Inject

class FirebaseFireStoreRepoImpl @Inject constructor(private val firebase: FirebaseFirestore) : FirebaseFireStoreRepo {
    override fun getLocalDateOrders(queryParam: String, startDate: Timestamp, endDate: Timestamp): Query {
        return  firebase.collection("orders")
            .whereEqualTo("orderStatus",queryParam)
            .whereGreaterThanOrEqualTo("timestamp",startDate)
            .whereLessThanOrEqualTo("timestamp",endDate)
    }

    override fun getLocalDateOrdersDetail(documentId: String): Task<DocumentSnapshot> {
        return firebase.collection("orders").document(documentId).get()
    }

    override fun updateOrderStatus(documentId: String, param: String): Task<Void> {
        return firebase.collection("orders").document(documentId).update("orderStatus",param)
    }
}