package com.serhatd.coupontracker.ui.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.serhatd.coupontracker.R
import com.serhatd.coupontracker.data.entity.Coupon
import com.serhatd.coupontracker.databinding.CardCouponBinding
import com.serhatd.coupontracker.ui.viewmodel.CouponsViewModel

class CouponAdapter(private val context: Context, private val viewModel: CouponsViewModel, private val coupons: List<Coupon>): RecyclerView.Adapter<CouponAdapter.CouponViewHolder>() {
    class CouponViewHolder(val binding: CardCouponBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CouponViewHolder {
        val binding: CardCouponBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.card_coupon, parent, false)
        return CouponViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CouponViewHolder, position: Int) {
        val coupon = coupons[position]

        holder.binding.couponAdapter = this
        holder.binding.coupon = coupon
    }

    fun openUrl(url: String) {
        Toast.makeText(context, "open $url", Toast.LENGTH_SHORT).show()
    }

    fun createReminder(coupon: Coupon) {
        Toast.makeText(context, "reminder ${coupon.code}", Toast.LENGTH_SHORT).show()
    }

    fun copyCouponCode(code: String) {
        Toast.makeText(context, "copy $code", Toast.LENGTH_SHORT).show()
    }

    fun editCoupon(coupon: Coupon) {
        Toast.makeText(context, "edit ${coupon.code}", Toast.LENGTH_SHORT).show()
    }

    fun shareCoupon(coupon: Coupon) {
        Toast.makeText(context, "share ${coupon.code}", Toast.LENGTH_SHORT).show()
    }

    fun deleteCoupon(coupon: Coupon) {
        Toast.makeText(context, "delete ${coupon.code}", Toast.LENGTH_SHORT).show()
    }

    override fun getItemCount(): Int {
        return coupons.size
    }
}