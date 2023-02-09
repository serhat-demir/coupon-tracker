package com.serhatd.coupontracker.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.serhatd.coupontracker.R
import com.serhatd.coupontracker.databinding.FragmentCouponsBinding

class CouponsFragment : Fragment() {
    private lateinit var binding: FragmentCouponsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_coupons, container, false)
        binding.fragmentCoupons = this

        return binding.root
    }
}