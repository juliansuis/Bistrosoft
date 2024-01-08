package com.juliansuis.bistrosoft.domain.repository

import com.juliansuis.bistrosoft.data.entity.LifecycleEvent
import kotlinx.coroutines.flow.Flow

interface LifecycleRepository {
    suspend fun insertEvent(event: LifecycleEvent)
    fun getAllEvents(): Flow<List<LifecycleEvent>>
}