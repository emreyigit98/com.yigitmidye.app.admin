package com.yigitmidye.app.admin.domain.use_case.firebase

import com.yigitmidye.app.admin.domain.model.OrdersModel
import com.yigitmidye.app.admin.domain.repo.firestore.FirebaseFireStoreRepo
import com.yigitmidye.app.admin.util.Resource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class GetOrdersDetailUseCase @Inject constructor(private val fireStoreRepo: FirebaseFireStoreRepo) {

    operator fun invoke(documentId : String) : Flow<Resource<OrdersModel>> = callbackFlow {
        trySend(Resource.Loading())
        fireStoreRepo.getLocalDateOrdersDetail(documentId = documentId).addOnSuccessListener { snapshot ->
            val response = snapshot.toObject(OrdersModel::class.java)
            trySend(Resource.Success(data = response ?: OrdersModel()))
        }.addOnFailureListener { exception ->
            trySend(Resource.Error(message = "Bir hata oluştu ${exception.localizedMessage}"))
        }
        awaitClose {  }
    }
}