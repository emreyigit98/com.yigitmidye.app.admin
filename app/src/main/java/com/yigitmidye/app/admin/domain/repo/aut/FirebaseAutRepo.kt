package com.yigitmidye.app.admin.domain.repo.aut

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface FirebaseAutRepo {

    fun userSignInWithEmailPassword(email : String,password : String) : Task<AuthResult>

}