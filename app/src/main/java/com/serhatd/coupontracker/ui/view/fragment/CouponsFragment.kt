package com.serhatd.coupontracker.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.serhatd.coupontracker.R
import com.serhatd.coupontracker.databinding.FragmentCouponsBinding
import com.serhatd.coupontracker.ui.view.adapter.CouponAdapter
import com.serhatd.coupontracker.ui.viewmodel.CouponsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CouponsFragment : Fragment() {
    private lateinit var binding: FragmentCouponsBinding
    private lateinit var viewModel: CouponsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel: CouponsViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_coupons, container, false)
        binding.fragmentCoupons = this

        initObservers()
        viewModel.getCoupons()

        return binding.root
    }

    private fun initObservers() {
        viewModel.coupons.observe(viewLifecycleOwner) {
            it?.let {
                val adapter = CouponAdapter(requireContext(), viewModel, it)
                binding.couponAdapter = adapter
            }
        }
    }

    fun navToAddCoupon(view: View) {
        Navigation.findNavController(view).navigate(R.id.couponsToAddCoupon)
    }
}