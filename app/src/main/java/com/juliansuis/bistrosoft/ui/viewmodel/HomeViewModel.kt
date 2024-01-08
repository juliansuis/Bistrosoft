package com.juliansuis.bistrosoft.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juliansuis.bistrosoft.data.networkUtils.NetworkResource
import com.juliansuis.bistrosoft.domain.repository.TimeRepository
import com.juliansuis.bistrosoft.ui.utils.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val timeRepository: TimeRepository,
) : ViewModel() {

    private val _uiState: MutableStateFlow<ScreenState<String>> = MutableStateFlow(
        ScreenState.Loading
    )
    val uiState: StateFlow<ScreenState<String>> = _uiState

    fun fetchTime() {
        viewModelScope.launch {
            timeRepository.fetchTime().collectLatest {
                _uiState.value = when (it) {
                    is NetworkResource.Error ->
                        ScreenState.Error(
                        it.e.message ?: "Oops! Something went wrong!"
                    )

                    is NetworkResource.Loading ->
                        ScreenState.Loading
                    is NetworkResource.Success ->
                        ScreenState.Success(it.data)
                }
            }
        }
    }
}