package com.serhatd.coupontracker.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currencies")
data class Currency(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    var id: Int,

    @ColumnInfo("name")
    var name: String,

    @ColumnInfo("symbol")
    var symbol: String
)