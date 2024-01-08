package com.juliansuis.bistrosoft.domain.repository

import com.juliansuis.bistrosoft.data.networkUtils.NetworkResource
import kotlinx.coroutines.flow.Flow

interface TimeRepository {
    suspend fun fetchTime(): Flow<NetworkResource<String>>
}
