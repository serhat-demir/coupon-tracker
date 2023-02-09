package com.serhatd.coupontracker.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.serhatd.coupontracker.R
import com.serhatd.coupontracker.data.room.DB
import com.serhatd.coupontracker.databinding.FragmentCouponsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CouponsFragment : Fragment() {
    private lateinit var binding: FragmentCouponsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_coupons, container, false)
        binding.fragmentCoupons = this

        val db = Room.databaseBuilder(requireContext(), DB::class.java, "coupontracker.sqlite")
            .createFromAsset("coupontracker.sqlite").build()

        val dao = db.getCouponDao()

        CoroutineScope(Dispatchers.IO).launch {
            val coupons = dao.getCoupons()

            withContext(Dispatchers.Main) {
                Toast.makeText(requireContext(), coupons[0].notes, Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }
}