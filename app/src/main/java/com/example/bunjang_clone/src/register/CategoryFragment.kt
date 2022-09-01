package com.example.bunjang_clone.src.register

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bunjang_clone.databinding.FragmentUploadCategoryBinding
import com.example.bunjang_clone.src.register.models.CategoryData
import com.example.bunjang_clone.src.register.models.RegisterResponse
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CategoryFragment(var mainCategory:String): BottomSheetDialogFragment() , RegisterActivityInterface {

    private lateinit var _binding: FragmentUploadCategoryBinding
    private val binding get() = _binding!!
    lateinit var categoryPass: OnDataCategory

    private lateinit var categoryAdapter : CategoryRvAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUploadCategoryBinding.inflate(layoutInflater, container, false)

        categoryRv()
        return binding.root
    }

    fun categoryRv() {
        RegisterService(this).getMainCategoryData()

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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        categoryPass = context as OnDataCategory
    }

    fun sendCategoryData(mainCategory: String){
        categoryPass.onDataCategory(mainCategory)
    }

    interface OnDataCategory {
        fun onDataCategory(mainCategory: String)
    }

    override fun onRegisterSuccess(response: RegisterResponse) {
    }

    override fun onRegisterFail(message: String) {
    }

    override fun onMainCategorySuccess(response: CategoryData) {
        var categoryList = response.result.toMutableList()

        categoryAdapter = CategoryRvAdapter(categoryList)
        binding.rvCategoryItem.adapter = categoryAdapter
        binding.rvCategoryItem.setHasFixedSize(true)

        categoryAdapter.categoryClickListener(object : CategoryRvAdapter.CategoryClickListener {
            override fun onClick(view: View, position: Int, name: String) {
                mainCategory = name
                sendCategoryData(mainCategory)
                dismiss()
            }

        })

    }

    override fun onMainCategoryFail(message: String) {
    }

    override fun onSubCategorySuccess(response: CategoryData) {
    }

    override fun onSubCategoryFail(message: String) {
    }
}