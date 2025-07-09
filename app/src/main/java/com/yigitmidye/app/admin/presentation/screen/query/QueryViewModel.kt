package com.yigitmidye.app.admin.presentation.screen.query

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Timestamp
import com.yigitmidye.app.admin.domain.use_case.firebase.GetQueryDateOrders
import com.yigitmidye.app.admin.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QueryViewModel @Inject constructor(private val getQueryDateOrders: GetQueryDateOrders) : ViewModel() {

    private val _queryUiState = MutableStateFlow<QueryUiState>(QueryUiState())
    val queryUiState : StateFlow<QueryUiState> get() = _queryUiState

    private var job : Job? = null

    fun fetchQueryDateList(startDate : Timestamp,endDate : Timestamp) {
        job = viewModelScope.launch {
            getQueryDateOrders.invoke(startDate = startDate, endDate = endDate).collect(
                collector = { result ->
                    when(result) {
                        is Resource.Error<*> -> _queryUiState.update { it.copy(loading = false, errorMessage = result.message, successful = null) }
                        is Resource.Loading<*> -> _queryUiState.update { it.copy(loading = true, errorMessage = null, successful = null) }
                        is Resource.Success<*> -> _queryUiState.update { it.copy(loading = false, errorMessage = null, successful = result.data) }
                    }
                }
            )
        }
    }
    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}