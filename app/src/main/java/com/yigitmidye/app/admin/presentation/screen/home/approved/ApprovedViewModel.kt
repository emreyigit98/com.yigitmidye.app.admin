package com.yigitmidye.app.admin.presentation.screen.home.approved

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
class ApprovedViewModel @Inject constructor(private val getOrdersUseCase: GetOrdersUseCase) : ViewModel() {

    private var job : Job? = null

    private val _approvedUiState = MutableStateFlow<ApprovedUiState>(ApprovedUiState())
    val approvedUiState : StateFlow<ApprovedUiState> get() = _approvedUiState

    init {
        fetchApprovedList()
    }

    private fun fetchApprovedList() {
        job = viewModelScope.launch {
            getOrdersUseCase.invoke("Siparişiniz onaylandı").collect { result ->
                when(result) {
                    is Resource.Error<*> -> {
                        _approvedUiState.update { it.copy(loading = false, errorMessage = result.message, approvedList = null) }
                    }
                    is Resource.Loading<*> -> {
                        _approvedUiState.update { it.copy(loading = true, errorMessage = null, approvedList = null) }
                    }
                    is Resource.Success<*> -> {
                        _approvedUiState.update { it.copy(loading = false, errorMessage = null, approvedList = result.data) }
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