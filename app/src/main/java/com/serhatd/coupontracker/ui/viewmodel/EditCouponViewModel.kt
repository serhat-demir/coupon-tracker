package com.serhatd.coupontracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.serhatd.coupontracker.data.repository.CouponTrackerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditCouponViewModel @Inject constructor(private val repo: CouponTrackerRepository): ViewModel() {
    val currencies = repo.currencies
    val navigationObserver = repo.navigationObserver
    val toastObserver = repo.toastObserver

    fun editCoupon(id: Int, url: String, code: String, notes: String, currency: String, discount: Int, expires_at: String) {
        repo.editCoupon(id, url, code, notes, currency, discount, expires_at)
    }

    fun getCurrencies() {
        repo.getCurrencies()
    }
}