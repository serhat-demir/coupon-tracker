package com.serhatd.coupontracker.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.serhatd.coupontracker.data.entity.Coupon

@Dao
interface CouponDao {
    @Query("SELECT * FROM coupons")
    suspend fun getCoupons(): List<Coupon>

    @Insert
    suspend fun addCoupon(coupon: Coupon)

    @Update
    suspend fun editCoupon(coupon: Coupon)
}