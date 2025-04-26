package com.yigitmidye.app.admin.domain.use_case.firebase

import com.yigitmidye.app.admin.domain.model.ShopModel
import com.yigitmidye.app.admin.domain.repo.firestore.FirebaseFireStoreRepo
import com.yigitmidye.app.admin.util.Resource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class GetShopUseCase @Inject constructor(private val firebaseFireStoreRepo: FirebaseFireStoreRepo) {

    operator fun invoke() : Flow<Resource<ShopModel>> {
        return callbackFlow {
            trySend(Resource.Loading())
            firebaseFireStoreRepo.getShopStatus().addOnSuccessListener { snapshot ->
                val response = snapshot?.toObject(ShopModel::class.java) ?: ShopModel()
                trySend(Resource.Success(data = response))
            }.addOnFailureListener { exception ->
                trySend(Resource.Error(message = "Beklenmedik bir hata oluştu ${exception.localizedMessage}"))
            }
            awaitClose {  }
        }
    }
}