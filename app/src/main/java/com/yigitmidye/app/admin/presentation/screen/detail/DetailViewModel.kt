package com.yigitmidye.app.admin.presentation.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yigitmidye.app.admin.domain.use_case.firebase.GetOrdersDetailUseCase
import com.yigitmidye.app.admin.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val getOrdersDetailUseCase: GetOrdersDetailUseCase) : ViewModel() {

    private val _detailUiState = MutableStateFlow<DetailUiState>(DetailUiState())
    val detailUiState : StateFlow<DetailUiState> get() = _detailUiState

    fun fetchOrderDetail(documentId : String) = viewModelScope.launch {
        getOrdersDetailUseCase.invoke(documentId = documentId).collect { result ->
            when(result) {
                is Resource.Error<*> -> {
                    _detailUiState.update { it.copy(loading = false, errorMessage = result.message, order = null) }
                }
                is Resource.Loading<*> -> {
                    _detailUiState.update { it.copy(loading = true, errorMessage = null, order = null) }
                }
                is Resource.Success<*> -> {
                    _detailUiState.update { it.copy(loading = false, errorMessage = null, order = result.data) }
                }
            }
        }
    }
}