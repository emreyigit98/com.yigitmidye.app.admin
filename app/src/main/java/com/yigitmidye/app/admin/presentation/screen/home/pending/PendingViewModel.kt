package com.yigitmidye.app.admin.presentation.screen.home.pending

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
class PendingViewModel @Inject constructor(private val getOrdersUseCase: GetOrdersUseCase) : ViewModel() {

    private var job : Job? = null

    private val _pendingUiState = MutableStateFlow<PendingUiState>(PendingUiState())
    val pendingUiState : StateFlow<PendingUiState> get() = _pendingUiState

    init {
        fetchPendingList()
    }

    private fun fetchPendingList() {
        job = viewModelScope.launch {
            getOrdersUseCase.invoke(queryParam = "Onay bekliyor").collect { result ->
                when(result) {
                    is Resource.Error<*> -> {
                        _pendingUiState.update { it.copy(loading = false, errorMessage = result.message, pendingList = null) }
                    }
                    is Resource.Loading<*> -> {
                        _pendingUiState.update { it.copy(loading = true, errorMessage = null, pendingList = null) }
                    }
                    is Resource.Success<*> -> {
                        _pendingUiState.update { it.copy(loading = false, errorMessage = null, pendingList = result.data) }
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