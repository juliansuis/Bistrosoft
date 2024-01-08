package com.juliansuis.bistrosoft.data.networkUtils

sealed interface NetworkResource<T: Any>  {
    class Loading<T: Any>: NetworkResource<T>
    data class Error<T: Any>(val e: Exception): NetworkResource<T>
    data class Success<T: Any>(val data: T): NetworkResource<T>
}