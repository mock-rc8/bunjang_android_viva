package com.example.bunjang_clone.src.register

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bunjang_clone.databinding.FragmentAreaBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AreaFragment(var myArea: String): BottomSheetDialogFragment() {

    private lateinit var _binding: FragmentAreaBinding
    private val binding get() = _binding!!
    lateinit var areaPass: OnDataArea

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAreaBinding.inflate(layoutInflater, container, false)

        if (myArea != null) {
            binding.etAreaLocationName.setText(myArea)
        }

        binding.btnAreaNext.setOnClickListener {
            myArea = if (binding.etAreaLocationName.text.toString() != "") {
                binding.etAreaLocationName.text.toString()
            } else {
                ""
            }
            sendAreaData(myArea)
            this.dismiss()
        }

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        areaPass = context as OnDataArea
    }

    fun sendAreaData(myArea: String){
        areaPass.onDataOption(myArea)
    }

    interface OnDataArea {
        fun onDataOption(myArea: String)
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
        return getWindowHeight() * 100 / 100
    }
    private fun getWindowHeight(): Int {
        val displayMetrics = DisplayMetrics()
        (context as Activity?)!!.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }
}