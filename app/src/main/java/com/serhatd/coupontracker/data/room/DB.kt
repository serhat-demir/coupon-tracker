package com.serhatd.coupontracker.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.serhatd.coupontracker.data.entity.Coupon
import com.serhatd.coupontracker.data.entity.Currency

@Database(entities = [Coupon::class, Currency::class], version = 1)
abstract class DB: RoomDatabase() {
    abstract fun getCouponDao(): CouponDao
    abstract fun getCurrencyDao(): CurrencyDao
}