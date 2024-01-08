package com.juliansuis.bistrosoft.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LifecycleEvent(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "event_name") val eventName: String,
    @ColumnInfo(name = "timestamp") val timestamp: Long
)
