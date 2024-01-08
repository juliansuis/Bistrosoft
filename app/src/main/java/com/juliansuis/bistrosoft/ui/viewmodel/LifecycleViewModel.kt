package com.juliansuis.bistrosoft.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juliansuis.bistrosoft.data.entity.LifecycleEvent
import com.juliansuis.bistrosoft.domain.repository.LifecycleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LifecycleViewModel @Inject constructor(
    private val repository: LifecycleRepository,
) : ViewModel() {

    val events: StateFlow<List<LifecycleEvent>> = repository.getAllEvents()
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    fun insertLifecycleEvent(event: LifecycleEvent) {
        viewModelScope.launch {
            repository.insertEvent(event)
        }
    }
}
