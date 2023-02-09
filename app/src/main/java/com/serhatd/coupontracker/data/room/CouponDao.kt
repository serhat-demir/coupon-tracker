package com.serhatd.coupontracker.data.room

import androidx.room.Dao
import androidx.room.Query
import com.serhatd.coupontracker.data.entity.Coupon

@Dao
interface CouponDao {
    @Query("SELECT * FROM coupons")
    suspend fun getCoupons(): List<Coupon>
}