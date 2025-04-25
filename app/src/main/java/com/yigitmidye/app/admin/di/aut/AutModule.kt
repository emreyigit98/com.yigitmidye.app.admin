package com.yigitmidye.app.admin.di.aut

import com.google.firebase.auth.FirebaseAuth
import com.yigitmidye.app.admin.data.repo.aut.FirebaseAutRepoImpl
import com.yigitmidye.app.admin.domain.repo.aut.FirebaseAutRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AutModule {

    @Provides
    @Singleton
    fun provideFirebaseAut() : FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseAutRepoImpl(firebaseAuth: FirebaseAuth) : FirebaseAutRepo {
        return FirebaseAutRepoImpl(firebaseAuth = firebaseAuth)
    }
}