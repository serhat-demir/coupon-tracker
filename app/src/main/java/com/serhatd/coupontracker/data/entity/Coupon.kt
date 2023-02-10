package com.serhatd.coupontracker.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "coupons")
data class Coupon (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    var id: Int,

    @ColumnInfo("url")
    var url: String,

    @ColumnInfo("code")
    var code: String,

    @ColumnInfo("notes")
    var notes: String,

    @ColumnInfo("currency")
    var currency: String,

    @ColumnInfo("discount")
    var discount: Int,

    @ColumnInfo("expires_at")
    var expires_at: String
): Serializable