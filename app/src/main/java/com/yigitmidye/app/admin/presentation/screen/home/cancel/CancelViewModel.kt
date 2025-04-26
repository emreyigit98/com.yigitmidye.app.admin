package com.yigitmidye.app.admin.presentation.screen.home.cancel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yigitmidye.app.admin.domain.use_case.firebase.GetOrdersUseCase
import com.yigitmidye.app.admin.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CancelViewModel @Inject constructor(private val getOrdersUseCase: GetOrdersUseCase) : ViewModel() {

    private var job : Job? = null

    private val _cancelUiState = MutableStateFlow<CancelUiState>(CancelUiState())
    val cancelUiState : StateFlow<CancelUiState> get() = _cancelUiState

    init {
        fetchCancelList()
    }

    private fun fetchCancelList() {
        job = viewModelScope.launch {
            getOrdersUseCase.invoke(queryParam = "Siparişiniz iptal edildi").collect { result ->
                when(result) {
                    is Resource.Error<*> -> {
                        _cancelUiState.update { it.copy(loading = false, errorMessage = null, cancelList = null) }
                    }
                    is Resource.Loading<*> -> {
                        _cancelUiState.update { it.copy(loading = true, errorMessage = result.message, cancelList = null) }
                    }
                    is Resource.Success<*> -> {
                        _cancelUiState.update { it.copy(loading = false, errorMessage = null, cancelList = result.data) }
                    }
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
        job = null
    }
}