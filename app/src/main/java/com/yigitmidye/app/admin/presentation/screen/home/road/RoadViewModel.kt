package com.yigitmidye.app.admin.presentation.screen.home.road

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
class RoadViewModel @Inject constructor(private val getOrdersUseCase: GetOrdersUseCase) : ViewModel() {

    private var job : Job? = null

    private val _roadUiState = MutableStateFlow<RoadUiState>(RoadUiState())
    val roadUiState : StateFlow<RoadUiState> get() = _roadUiState

    init {
        fetchRoadList()
    }

    private fun fetchRoadList() {
        job = viewModelScope.launch {
            getOrdersUseCase.invoke(queryParam = "Siparişiniz yola çıktı").collect { result ->
                when(result) {
                    is Resource.Error<*> -> {
                        _roadUiState.update { it.copy(loading = false, errorMessage = result.message, roadList = null) }
                    }
                    is Resource.Loading<*> -> {
                        _roadUiState.update { it.copy(loading = true, errorMessage = null, roadList = null) }
                    }
                    is Resource.Success<*> -> {
                        _roadUiState.update { it.copy(loading = false, errorMessage = null, roadList = result.data) }
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