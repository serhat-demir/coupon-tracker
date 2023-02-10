package com.serhatd.coupontracker.ui.view.fragment

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.serhatd.coupontracker.R
import com.serhatd.coupontracker.databinding.FragmentEditCouponBinding
import com.serhatd.coupontracker.ui.viewmodel.EditCouponViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class EditCouponFragment : Fragment() {
    private lateinit var binding: FragmentEditCouponBinding
    private lateinit var viewModel: EditCouponViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: EditCouponViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_coupon, container, false)
        binding.fragmentEditCoupon = this

        initObservers()
        viewModel.getCurrencies()

        return binding.root
    }

    fun editCoupon(id: Int, url: String, notes: String, code: String, currency: String, discount: Int, expires_at: String) {
        val currencyIndex = viewModel.currencies.value!!.map { it.name }.indexOf(currency)
        val currencySymbol = viewModel.currencies.value!!.map { it.symbol }[currencyIndex]

        viewModel.editCoupon(id, url, notes, code, currencySymbol, discount, expires_at)
    }

    @SuppressLint("SetTextI18n")
    fun selectDate() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(requireContext(), { _, i, i2, i3 ->
            val dayStr = if (i3 < 10) "0$i3" else i3
            val monthStr = if (i2 < 10) "0${i2 + 1}" else i2 + 1

            binding.frgEditCouponTxtExpireDate.setText("$monthStr-$dayStr-$i")
        }, year, month, day)

        datePicker.setTitle(getString(R.string.date_picker_title))
        datePicker.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.btn_save), datePicker)
        datePicker.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.btn_cancel), datePicker)
        datePicker.show()
    }

    private fun getBundle() {
        val bundle: EditCouponFragmentArgs by navArgs()
        binding.coupon = bundle.coupon
        binding.frgEditCouponSpCurrency.selectItemByIndex(viewModel.currencies.value!!.map { it.symbol }.indexOf(bundle.coupon.currency))
    }

    private fun initObservers() {
        viewModel.currencies.observe(viewLifecycleOwner) {
            it?.let {
                binding.frgEditCouponSpCurrency.setItems(it.map { it1 -> it1.name })
                getBundle()
            }
        }

        viewModel.navigationObserver.observe(viewLifecycleOwner) {
            it?.let {
                if (it) requireActivity().onBackPressedDispatcher.onBackPressed()
            }
        }

        viewModel.toastObserver.observe(viewLifecycleOwner) {
            it?.let {
                if (it.isNotEmpty()) Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}