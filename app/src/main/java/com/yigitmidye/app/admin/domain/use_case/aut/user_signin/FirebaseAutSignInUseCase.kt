package com.yigitmidye.app.admin.domain.use_case.aut.user_signin

import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthException
import com.yigitmidye.app.admin.domain.repo.aut.FirebaseAutRepo
import com.yigitmidye.app.admin.util.Resource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class FirebaseAutSignInUseCase @Inject constructor(private val firebaseAutRepo: FirebaseAutRepo) {

    operator fun invoke(email : String,password : String) : Flow<Resource<Boolean>> {
        return callbackFlow {
            send(Resource.Loading())
            firebaseAutRepo.userSignInWithEmailPassword(email, password).addOnSuccessListener {
                trySend(Resource.Success(data = true))
            }.addOnFailureListener { exception ->
                if (exception is FirebaseAuthException) {
                    when(exception.errorCode) {
                        "ERROR_INVALID_EMAIL" -> { trySend(Resource.Error(message = "Girilen e-posta adresi geçerli bir e-posta formatına sahip değildir.")) }
                        "ERROR_USER_DISABLED" -> { trySend(Resource.Error(message = "Giriş yapılmaya çalışılan kullanıcı hesabı devre dışı bırakılmıştır.")) }
                        "ERROR_USER_NOT_FOUND" -> { trySend(Resource.Error(message = "Giriş yapılmaya çalışılan kullanıcı bulunamamıştır.")) }
                        "ERROR_WRONG_PASSWORD" -> {trySend(Resource.Error(message = "Girilen şifre yanlış."))}
                        "ERROR_EMAIL_ALREADY_IN_USE" -> {trySend(Resource.Error(message = "Girilen e-posta adresi, başka bir kullanıcı tarafından zaten kullanılıyor."))}
                        "ERROR_TOO_MANY_REQUESTS" -> {trySend(Resource.Error(message = "Çok fazla giriş denemesi yapılmış ve geçici bir engelleme uygulanmıştır."))}
                        "ERROR_INVALID_CREDENTIAL" -> {trySend(Resource.Error(message = "Girilen kimlik bilgileri geçersizdir "))}
                        "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL" -> {trySend(Resource.Error(message = "E-posta adresi zaten bir başka kimlik doğrulama yöntemiyle kullanılıyor."))}
                        else -> {
                            trySend(Resource.Error(message = "Bilinmeyen bir hata oluştu ${exception.localizedMessage}"))
                        }
                    }
                }else if (exception is FirebaseNetworkException) {
                    trySend(Resource.Error(message = "İnternet bağlantınız yok,lütfen internet bağlantınızı kontrol ediniz."))
                }else {
                    trySend(Resource.Error(message = "Bilinmeyen bir hata oluştu ${exception.localizedMessage}"))
                }
            }
            awaitClose {  }
        }
    }
}