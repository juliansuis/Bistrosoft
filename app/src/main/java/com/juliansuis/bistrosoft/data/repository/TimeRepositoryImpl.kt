package com.juliansuis.bistrosoft.data.repository

import com.juliansuis.bistrosoft.data.network.ApiService
import com.juliansuis.bistrosoft.data.networkUtils.NetworkResource
import com.juliansuis.bistrosoft.domain.repository.TimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

class TimeRepositoryImpl @Inject constructor(private val api: ApiService) : TimeRepository {
    override suspend fun fetchTime(): Flow<NetworkResource<String>> = flow {
        emit(NetworkResource.Loading())
        val response = api.fetchTime()
        if (response.isSuccessful) {
            val data = response.body()
            emit (
                if (data != null) {
                    val datetime = formatDateTime(data.currentDateTime)
                    NetworkResource.Success(datetime)
            } else {
                    NetworkResource.Error(Exception(response.message()))
                }
            )
        } else {
            emit(NetworkResource.Error(Exception(response.message())))
        }
    }

    private fun formatDateTime(input: String): String {
        val inputFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mmXXX", Locale.getDefault())
        val dateTime = inputFormatter.parse(input)

        val outputFormatter = SimpleDateFormat("dd-MM-yy HH:mm", Locale.getDefault())
        return outputFormatter.format(dateTime!!)
    }
}