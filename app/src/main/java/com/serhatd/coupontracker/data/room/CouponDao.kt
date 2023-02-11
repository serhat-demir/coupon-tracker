package com.serhatd.coupontracker.data.room

import androidx.room.*
import com.serhatd.coupontracker.data.entity.Coupon

@Dao
interface CouponDao {
    @Query("SELECT * FROM coupons ORDER BY id DESC")
    suspend fun getCoupons(): List<Coupon>

    @Insert
    suspend fun addCoupon(coupon: Coupon)

    @Update
    suspend fun editCoupon(coupon: Coupon)

    @Delete
    suspend fun deleteCoupon(coupon: Coupon)
}