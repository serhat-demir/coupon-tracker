package com.serhatd.coupontracker.data.repository

import androidx.lifecycle.MutableLiveData
import com.serhatd.coupontracker.data.entity.Coupon
import com.serhatd.coupontracker.data.entity.Currency
import com.serhatd.coupontracker.data.room.CouponDao
import com.serhatd.coupontracker.data.room.CurrencyDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CouponTrackerRepository(private val couponDao: CouponDao, private val currencyDao: CurrencyDao) {
    val coupons = MutableLiveData<List<Coupon>>()
    val currencies = MutableLiveData<List<Currency>>()

    val navigationObserver = MutableLiveData<Boolean>()

    fun getCoupons() {
        CoroutineScope(Dispatchers.IO).launch {
            val couponList = couponDao.getCoupons()

            withContext(Dispatchers.Main) {
                coupons.value = couponList
            }
        }
    }

    fun addCoupon(url: String, notes: String, currency: String, discount: Int, expires_at: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val coupon = Coupon(0, url, notes, currency, discount, expires_at)
            couponDao.addCoupon(coupon)

            withContext(Dispatchers.Main) {
                navigationObserver.value = true
            }
        }
    }

    fun editCoupon(id: Int, url: String, notes: String, currency: String, discount: Int, expires_at: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val coupon = Coupon(id, url, notes, currency, discount, expires_at)
            couponDao.editCoupon(coupon)
        }
    }

    fun getCurrencies() {
        CoroutineScope(Dispatchers.IO).launch {
            val currencyList = currencyDao.getCurrencies()

            withContext(Dispatchers.Main) {
                currencies.value = currencyList
            }
        }
    }
}