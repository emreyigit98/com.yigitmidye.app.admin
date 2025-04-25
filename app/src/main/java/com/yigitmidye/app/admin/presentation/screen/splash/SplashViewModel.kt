package com.yigitmidye.app.admin.presentation.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {

    private val _nextScreen = MutableStateFlow(false)
    val nextScreen : StateFlow<Boolean> get() = _nextScreen

    init {
        viewModelScope.launch {
            delay(2000)
            _nextScreen.value = true
        }
    }
}