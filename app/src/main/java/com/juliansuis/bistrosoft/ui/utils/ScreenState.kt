package com.juliansuis.bistrosoft.ui.utils

sealed interface ScreenState<out T> {
    data object Loading: ScreenState<Nothing>
    data class Error(val message: String): ScreenState<Nothing>
    data class Success<T>(val data: T): ScreenState<T>
}