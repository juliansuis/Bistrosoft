package com.juliansuis.bistrosoft.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.juliansuis.bistrosoft.data.dao.LifecycleEventDao
import com.juliansuis.bistrosoft.data.entity.LifecycleEvent

@Database(entities = [LifecycleEvent::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun lifecycleEventDao(): LifecycleEventDao

    companion object {
        const val DB_NAME = "app_database"
    }
}