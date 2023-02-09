package com.serhatd.coupontracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.serhatd.coupontracker.data.repository.CouponTrackerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddCouponViewModel @Inject constructor(private val repo: CouponTrackerRepository): ViewModel() {
    val currencies = repo.currencies
    val navigationObserver = repo.navigationObserver

    fun addCoupon(url: String, notes: String, code: String, currency: String, discount: Int, expires_at: String) {
        repo.addCoupon(url, notes, code, currency, discount, expires_at)
    }

    fun getCurrencies() {
        repo.getCurrencies()
    }
}