package com.example.bunjang_clone.src.register

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bunjang_clone.databinding.FragmentOptionDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class OptionDialogFragment(var itemCnt:Int, var isExchange: Boolean, var isNew: Boolean): BottomSheetDialogFragment() {
    private lateinit var _binding: FragmentOptionDialogBinding
    private val binding get() = _binding!!
    lateinit var dataPass: OnDataPass

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentOptionDialogBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dataPass = context as OnDataPass
    }

    fun passData(itemCnt: Int, isExchange: Boolean, isNew: Boolean){
        dataPass.onDataPass(itemCnt, isExchange, isNew)
    }

    interface OnDataPass {
        fun onDataPass(itemCnt: Int, isExchange: Boolean, isNew: Boolean)
    }
}