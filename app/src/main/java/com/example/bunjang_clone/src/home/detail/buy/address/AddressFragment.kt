package com.example.bunjang_clone.src.home.detail.buy.address

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bunjang_clone.databinding.DialogAddressBinding
import com.example.bunjang_clone.src.home.detail.buy.DeliveryBuyActivity
import com.example.bunjang_clone.src.home.detail.buy.address.models.AddressData
import com.example.bunjang_clone.src.home.detail.buy.address.models.AddressDialogRvAdapter
import com.example.bunjang_clone.src.home.detail.buy.address.models.AddressResponse
import com.example.bunjang_clone.src.home.detail.buy.address.models.GetAddressData
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddressFragment : BottomSheetDialogFragment() {

    private lateinit var binding: DialogAddressBinding

    private lateinit var addressDialogAdapter : AddressDialogRvAdapter
    var dataList = ArrayList<AddressData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DialogAddressBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener {
            val bottomSheetDialog = it as BottomSheetDialog
            setupRatio(bottomSheetDialog)
        }
        return dialog
    }

    private fun setupRatio(bottomSheetDialog: BottomSheetDialog) {
        val bottomSheet = bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as View
        val behavior = BottomSheetBehavior.from<View>(bottomSheet)
        val layoutParams = bottomSheet!!.layoutParams
        layoutParams.height = getBottomSheetDialogDefaultHeight()
        bottomSheet.layoutParams = layoutParams
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun getBottomSheetDialogDefaultHeight(): Int {
        return getWindowHeight() * 30 / 100
    }
    private fun getWindowHeight(): Int {
        val displayMetrics = DisplayMetrics()
        (context as Activity?)!!.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

}