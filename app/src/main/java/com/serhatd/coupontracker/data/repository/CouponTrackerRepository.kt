package com.serhatd.coupontracker.data.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.serhatd.coupontracker.R
import com.serhatd.coupontracker.data.entity.Coupon
import com.serhatd.coupontracker.data.entity.Currency
import com.serhatd.coupontracker.data.room.CouponDao
import com.serhatd.coupontracker.data.room.CurrencyDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CouponTrackerRepository(private val context: Context, private val couponDao: CouponDao, private val currencyDao: CurrencyDao) {
    val coupons = MutableLiveData<List<Coupon>>()
    val currencies = MutableLiveData<List<Currency>>()

    val navigationObserver = MutableLiveData<Boolean>()
    val toastObserver = MutableLiveData<String>()

    fun getCoupons() {
        CoroutineScope(Dispatchers.IO).launch {
            val couponList = couponDao.getCoupons()

            withContext(Dispatchers.Main) {
                coupons.value = couponList
            }
        }
    }

    fun addCoupon(url: String, code: String, notes: String, currency: String, discount: Int, expires_at: String) {
        if (url.isNotEmpty() && notes.isNotEmpty() && code.isNotEmpty() && currency.isNotEmpty() && discount > 0 && expires_at.isNotEmpty())
            CoroutineScope(Dispatchers.IO).launch {
                val coupon = Coupon(0, url, code, notes, currency, discount, expires_at)
                couponDao.addCoupon(coupon)

                withContext(Dispatchers.Main) {
                    navigationObserver.value = true
                    navigationObserver.value = false
                }
            }
        else {
            toastObserver.value = context.getString(R.string.msg_fields_cannot_be_empty)
            toastObserver.value = ""
        }
    }

    fun editCoupon(id: Int, url: String, code: String, notes: String, currency: String, discount: Int, expires_at: String) {
        if (url.isNotEmpty() && notes.isNotEmpty() && code.isNotEmpty() && currency.isNotEmpty() && discount > 0 && expires_at.isNotEmpty())
            CoroutineScope(Dispatchers.IO).launch {
                val coupon = Coupon(id, url, code, notes, currency, discount, expires_at)
                couponDao.editCoupon(coupon)

                withContext(Dispatchers.Main) {
                    navigationObserver.value = true
                    navigationObserver.value = false
                }
            }
        else {
            toastObserver.value = context.getString(R.string.msg_fields_cannot_be_empty)
            toastObserver.value = ""
        }
    }

    fun deleteCoupon(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val coupon = Coupon(id, "", "", "", "", 0, "")
            couponDao.deleteCoupon(coupon)
            getCoupons()
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