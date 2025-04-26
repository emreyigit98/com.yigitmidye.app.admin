package com.yigitmidye.app.admin.presentation.screen.home.delivery

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
class DeliveryViewModel @Inject constructor(private val getOrdersUseCase: GetOrdersUseCase) : ViewModel() {

    private var job : Job? = null

    private val _deliveryUiState = MutableStateFlow<DeliveryUiState>(DeliveryUiState())
    val deliveryUiState : StateFlow<DeliveryUiState> get() = _deliveryUiState

    init {
        fetchDeliveryList()
    }

    private fun fetchDeliveryList() {
        job = viewModelScope.launch {
            getOrdersUseCase.invoke(queryParam = "Siparişiniz teslim edildi").collect { result ->
                when(result) {
                    is Resource.Error<*> -> {
                        _deliveryUiState.update { it.copy(loading = false, errorMessage = result.message, deliveryList = null) }
                    }
                    is Resource.Loading<*> -> {
                        _deliveryUiState.update { it.copy(loading = true, errorMessage = null, deliveryList = null) }
                    }
                    is Resource.Success<*> -> {
                        _deliveryUiState.update { it.copy(loading = false, errorMessage = null, deliveryList = result.data) }
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