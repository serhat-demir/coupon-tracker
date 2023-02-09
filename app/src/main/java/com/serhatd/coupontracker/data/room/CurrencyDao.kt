package com.serhatd.coupontracker.data.room

import androidx.room.Dao
import androidx.room.Query
import com.serhatd.coupontracker.data.entity.Currency

@Dao
interface CurrencyDao {
    @Query("SELECT * FROM currencies")
    suspend fun getCurrencies(): List<Currency>
}