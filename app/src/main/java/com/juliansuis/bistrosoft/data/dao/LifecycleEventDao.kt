package com.juliansuis.bistrosoft.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.juliansuis.bistrosoft.data.entity.LifecycleEvent
import kotlinx.coroutines.flow.Flow

@Dao
interface LifecycleEventDao {
    @Insert
    suspend fun insertEvent(event: LifecycleEvent)

    @Query("SELECT * FROM LifecycleEvent ORDER BY timestamp DESC")
    fun getAllEvents(): Flow<List<LifecycleEvent>>
}
