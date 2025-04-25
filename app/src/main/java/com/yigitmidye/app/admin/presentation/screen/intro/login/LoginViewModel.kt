package com.yigitmidye.app.admin.presentation.screen.intro.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yigitmidye.app.admin.domain.use_case.aut.user_signin.FirebaseAutSignInUseCase
import com.yigitmidye.app.admin.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val firebaseAutSignInUseCase: FirebaseAutSignInUseCase) : ViewModel() {

    private val _loginUiState = MutableStateFlow<LoginUiState>(LoginUiState())
    val loginUiState : StateFlow<LoginUiState> get() = _loginUiState

    fun userSignInWithEmailAndPassword(email : String,password : String) {
        viewModelScope.launch {
            firebaseAutSignInUseCase.invoke(email = email, password = password).collect { result ->
                when(result) {
                    is Resource.Error<*> -> {
                        _loginUiState.update { it.copy(loading = false, errorMessage = result.message,success = false) }
                    }
                    is Resource.Loading<*> -> {
                        _loginUiState.update { it.copy(loading = true, errorMessage = null,success = false) }
                    }
                    is Resource.Success<*> -> {
                        _loginUiState.update { it.copy(loading = false, errorMessage = null, success = result.data ?: false) }
                    }
                }
            }
        }
    }
}