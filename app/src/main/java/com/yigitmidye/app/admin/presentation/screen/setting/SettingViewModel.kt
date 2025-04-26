package com.yigitmidye.app.admin.presentation.screen.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.yigitmidye.app.admin.domain.use_case.firebase.GetShopUseCase
import com.yigitmidye.app.admin.domain.use_case.firebase.UpdateShopUseCase
import com.yigitmidye.app.admin.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val updateShopUseCase: UpdateShopUseCase,
    private val getShopUseCase: GetShopUseCase
) : ViewModel() {

    private var job: Job? = null

    private val _getShopUiState = MutableStateFlow<GetShopUiState>(GetShopUiState())
    val getShopUiState: StateFlow<GetShopUiState> get() = _getShopUiState

    private val _updateShopUiState = MutableStateFlow<UpdateShopUiState>(UpdateShopUiState())
    val updateShopUiState: StateFlow<UpdateShopUiState> get() = _updateShopUiState

    init {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val token = task.result
                FirebaseFirestore.getInstance().collection("adminTokens")
                    .document("allTokens")
                    .set(
                        hashMapOf("fcmToken" to FieldValue.arrayUnion(token)),
                        SetOptions.merge()
                    )
            }
        }
    }

    fun getShopStatus() {
        job = viewModelScope.launch {
            getShopUseCase.invoke().collect { result ->
                when (result) {
                    is Resource.Error<*> -> {
                        _getShopUiState.update {
                            it.copy(
                                loading = false,
                                errorMessage = result.message,
                                shopStatus = null
                            )
                        }
                    }

                    is Resource.Loading<*> -> {
                        _getShopUiState.update {
                            it.copy(
                                loading = true,
                                errorMessage = null,
                                shopStatus = null
                            )
                        }
                    }

                    is Resource.Success<*> -> {
                        _getShopUiState.update {
                            it.copy(
                                loading = false,
                                errorMessage = null,
                                shopStatus = result.data
                            )
                        }
                    }
                }
            }
        }
    }

    fun updateShop(param: Boolean) = viewModelScope.launch {
        updateShopUseCase.invoke(param = param).collect { result ->
            when (result) {
                is Resource.Error<*> -> {
                    _updateShopUiState.update {
                        it.copy(
                            loading = false,
                            errorMessage = result.message,
                            success = false
                        )
                    }
                }

                is Resource.Loading<*> -> {
                    _updateShopUiState.update {
                        it.copy(
                            loading = true,
                            errorMessage = null,
                            success = false
                        )
                    }
                }

                is Resource.Success<*> -> {
                    _updateShopUiState.update {
                        it.copy(
                            loading = false,
                            errorMessage = null,
                            success = result.data
                        )
                    }
                    getShopStatus()
                }
            }
        }
    }
}