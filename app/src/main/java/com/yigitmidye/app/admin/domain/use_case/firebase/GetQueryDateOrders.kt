package com.yigitmidye.app.admin.domain.use_case.firebase

import com.google.firebase.Timestamp
import com.yigitmidye.app.admin.domain.model.OrdersModel
import com.yigitmidye.app.admin.domain.repo.firestore.FirebaseFireStoreRepo
import com.yigitmidye.app.admin.util.Resource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class GetQueryDateOrders @Inject constructor(private val firebaseFireStoreRepo: FirebaseFireStoreRepo) {

    operator fun invoke(
        startDate: Timestamp,
        endDate: Timestamp
    ): Flow<Resource<List<OrdersModel>>> = callbackFlow {
        trySend(Resource.Loading())
        firebaseFireStoreRepo.getQueryDateOrders(startDate, endDate)
            .addOnSuccessListener { snapshot ->
                val queryResponse = snapshot?.documents?.mapNotNull { documentSnapshot ->
                    documentSnapshot.toObject(OrdersModel::class.java)
                        ?.copy(documentId = documentSnapshot.id)
                }
                trySend(Resource.Success(data = queryResponse ?: emptyList()))
            }.addOnFailureListener { exception ->
            trySend(Resource.Error(message = "Hata ${exception.localizedMessage}"))
        }
        awaitClose { }
    }
}