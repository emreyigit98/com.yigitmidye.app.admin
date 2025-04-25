package com.yigitmidye.app.admin.di.firebase

import com.google.firebase.firestore.FirebaseFirestore
import com.yigitmidye.app.admin.data.repo.firestore.FirebaseFireStoreRepoImpl
import com.yigitmidye.app.admin.domain.repo.firestore.FirebaseFireStoreRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FireStoreModule {

    @Singleton
    @Provides
    fun provideFirebaseFireStore() : FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Singleton
    @Provides
    fun provideFirebaseFireStoreRepoImpl(firebase : FirebaseFirestore) : FirebaseFireStoreRepo {
        return FirebaseFireStoreRepoImpl(firebase = firebase)
    }
}