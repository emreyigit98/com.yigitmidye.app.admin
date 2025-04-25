package com.yigitmidye.app.admin.domain.use_case.firebase


import com.yigitmidye.app.admin.domain.model.OrdersModel
import com.yigitmidye.app.admin.domain.repo.firestore.FirebaseFireStoreRepo
import com.yigitmidye.app.admin.util.Resource
import com.yigitmidye.app.admin.util.setPairTimesTamp
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class GetOrdersUseCase @Inject constructor(private val firebaseFireStoreRepo: FirebaseFireStoreRepo) {

    operator fun invoke(queryParam : String) : Flow<Resource<List<OrdersModel>>> = callbackFlow {
        val (start,end) = setPairTimesTamp()
        trySend(Resource.Loading())
        val listenerRegistration = firebaseFireStoreRepo.getLocalDateOrders(queryParam,start,end).addSnapshotListener { snapshot, error ->
            error?.let { exception ->
                trySend(Resource.Error(message = "Siparişler alınırken bir hata oluştu ${exception.localizedMessage}"))
                return@addSnapshotListener
            }
            val response = snapshot?.documents?.mapNotNull { doc -> doc.toObject(OrdersModel::class.java)?.copy(documentId = doc.id) }
            trySend(Resource.Success(data = response ?: emptyList()))
        }
        awaitClose { listenerRegistration.remove() }
    }
}
