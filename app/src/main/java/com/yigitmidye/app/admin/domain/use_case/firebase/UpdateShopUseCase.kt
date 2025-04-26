package com.yigitmidye.app.admin.domain.use_case.firebase

import com.yigitmidye.app.admin.domain.repo.firestore.FirebaseFireStoreRepo
import com.yigitmidye.app.admin.util.Resource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class UpdateShopUseCase @Inject constructor(private val firebaseFireStoreRepo: FirebaseFireStoreRepo) {

    operator fun invoke(param : Boolean) : Flow<Resource<Boolean>> {
        return callbackFlow {
            trySend(Resource.Loading())
            firebaseFireStoreRepo.updateShop(param = param).addOnSuccessListener {
                trySend(Resource.Success(data = true))
            }.addOnFailureListener { exception ->
                trySend(Resource.Error(message = "Beklenmeyen bir hata oluştu ${exception.localizedMessage}"))
            }
            awaitClose {  }
        }
    }
}