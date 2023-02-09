package com.serhatd.coupontracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.serhatd.coupontracker.data.repository.CouponTrackerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CouponsViewModel @Inject constructor(private val repo: CouponTrackerRepository): ViewModel() {
    val coupons = repo.coupons

    fun getCoupons() {
        repo.getCoupons()
    }

    fun editCoupon(id: Int, url: String, notes: String, currency: String, discount: Int, expires_at: String) {
        repo.editCoupon(id, url, notes, currency, discount, expires_at)
    }
}