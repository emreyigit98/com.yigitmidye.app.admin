package com.yigitmidye.app.admin.data.repo.aut

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.yigitmidye.app.admin.domain.repo.aut.FirebaseAutRepo
import javax.inject.Inject

class FirebaseAutRepoImpl @Inject constructor(private val firebaseAuth: FirebaseAuth) : FirebaseAutRepo {
    override fun userSignInWithEmailPassword(email: String, password: String): Task<AuthResult> {
        return firebaseAuth.signInWithEmailAndPassword(email,password)
    }
}