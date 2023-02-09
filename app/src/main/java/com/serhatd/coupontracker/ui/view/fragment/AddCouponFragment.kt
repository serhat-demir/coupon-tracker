package com.serhatd.coupontracker.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.serhatd.coupontracker.R
import com.serhatd.coupontracker.databinding.FragmentAddCouponBinding
import com.serhatd.coupontracker.ui.viewmodel.AddCouponViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCouponFragment : Fragment() {
    private lateinit var binding: FragmentAddCouponBinding
    private lateinit var viewModel: AddCouponViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel: AddCouponViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_coupon, container, false)
        binding.fragmentAddCoupon = this

        initObservers()
        viewModel.getCurrencies()

        return binding.root
    }

    private fun initObservers() {
        viewModel.currencies.observe(viewLifecycleOwner) {
            it?.let {

            }
        }

        viewModel.navigationObserver.observe(viewLifecycleOwner) {
            it?.let {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
        }
    }
}