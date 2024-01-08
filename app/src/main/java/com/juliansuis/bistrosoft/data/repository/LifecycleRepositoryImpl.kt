package com.juliansuis.bistrosoft.data.repository

import androidx.lifecycle.LiveData
import com.juliansuis.bistrosoft.data.dao.LifecycleEventDao
import com.juliansuis.bistrosoft.data.entity.LifecycleEvent
import com.juliansuis.bistrosoft.domain.repository.LifecycleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LifecycleRepositoryImpl @Inject constructor(
    private val dao: LifecycleEventDao,
) : LifecycleRepository {
    override suspend fun insertEvent(event: LifecycleEvent) {
        dao.insertEvent(event)
    }

    override fun getAllEvents(): Flow<List<LifecycleEvent>> = dao.getAllEvents()
}
