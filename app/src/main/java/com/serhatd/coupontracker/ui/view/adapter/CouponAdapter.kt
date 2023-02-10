package com.serhatd.coupontracker.ui.view.adapter

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.serhatd.coupontracker.R
import com.serhatd.coupontracker.data.entity.Coupon
import com.serhatd.coupontracker.databinding.CardCouponBinding
import com.serhatd.coupontracker.ui.view.fragment.CouponsFragmentDirections
import com.serhatd.coupontracker.ui.viewmodel.CouponsViewModel
import java.text.SimpleDateFormat
import java.util.*

class CouponAdapter(private val context: Context, private val viewModel: CouponsViewModel, private val coupons: List<Coupon>): RecyclerView.Adapter<CouponAdapter.CouponViewHolder>() {
    @SuppressLint("SimpleDateFormat")
    private val formatter = SimpleDateFormat(context.getString(R.string.date_format))

    class CouponViewHolder(val binding: CardCouponBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CouponViewHolder {
        val binding: CardCouponBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.card_coupon, parent, false)
        return CouponViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CouponViewHolder, position: Int) {
        val coupon = coupons[position]

        holder.binding.couponAdapter = this
        holder.binding.coupon = coupon

        val expireDate = formatter.parse(coupon.expires_at)
        val currentDate = Date()

        holder.binding.couponExpired = (expireDate?.compareTo(currentDate) == -1)
    }

    fun openUrl(url: String) {
        var newUrl = url

        if (!url.startsWith(context.getString(R.string.http_prefix)) && !url.startsWith(context.getString(R.string.https_prefix)))
            newUrl = context.getString(R.string.http_prefix) + url

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(newUrl))
        context.startActivity(intent)
    }

    fun createReminder(coupon: Coupon) {
        val expireDate = formatter.parse(coupon.expires_at)

        val intent = Intent(Intent.ACTION_EDIT)
        intent.type = context.getString(R.string.calendar_event_intent)
        intent.putExtra(context.getString(R.string.calendar_event_label_title), context.getString(R.string.calendar_event_title_prefix) + coupon.url)
        intent.putExtra(context.getString(R.string.calendar_event_label_description), context.getString(R.string.calendar_event_description))
        intent.putExtra(context.getString(R.string.calendar_event_label_begin_time), expireDate?.time)
        intent.putExtra(context.getString(R.string.calendar_event_label_end_time), expireDate?.time)
        intent.putExtra(context.getString(R.string.calendar_event_label_time), true)
        intent.putExtra(context.getString(R.string.calendar_event_label_rule), context.getString(R.string.calendar_event_rule))

        context.startActivity(intent)
    }

    fun copyCouponCode(code: String) {
        val clipboard: ClipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip: ClipData = ClipData.newPlainText("", code)
        clipboard.setPrimaryClip(clip)

        Toast.makeText(context, context.getString(R.string.msg_copied_to_clipboard), Toast.LENGTH_SHORT).show()
    }

    fun editCoupon(coupon: Coupon, view: View) {
        val couponsToEditCoupon = CouponsFragmentDirections.couponsToEditCoupon(coupon)
        Navigation.findNavController(view).navigate(couponsToEditCoupon)
    }

    fun shareCoupon(coupon: Coupon) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = context.getString(R.string.share_coupon_intent)
        intent.putExtra(Intent.EXTRA_TEXT, context.getString(R.string.share_coupon_subject_prefix).format(coupon.url, coupon.discount.toString(), coupon.currency, coupon.code))

        context.startActivity(Intent.createChooser(intent, context.getString(R.string.share_coupon_label)))
    }

    fun deleteCoupon(coupon: Coupon) {
        val builder = AlertDialog.Builder(context, R.style.DialogTheme)
        builder.setTitle(context.getString(R.string.title_delete_coupon))
        builder.setMessage(context.getString(R.string.message_delete_coupon))
        builder.setNegativeButton(context.getString(R.string.button_cancel), null)
        builder.setPositiveButton(context.getString(R.string.btn_delete)) { _, _ ->
            viewModel.deleteCoupon(coupon.id)
        }
        builder.create().show()
    }

    override fun getItemCount(): Int {
        return coupons.size
    }
}