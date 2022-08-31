package com.example.bunjang_clone.src.register

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.bunjang_clone.R
import com.example.bunjang_clone.databinding.FragmentOptionDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class OptionDialogFragment(var itemCnt:Int, var itemStatus: Boolean, var itemexchange: Boolean): BottomSheetDialogFragment() {
    private lateinit var _binding: FragmentOptionDialogBinding
    private val binding get() = _binding!!
    lateinit var optionPass: OnDataOption

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentOptionDialogBinding.inflate(layoutInflater, container, false)

        if (itemCnt > 0) {
            binding.etOptionCount.setText(itemCnt.toString())
        }
        binding.tvOptionBtnOld.setOnClickListener {
            itemStatus = false
            binding.tvOptionBtnOld.setTextColor(ContextCompat.getColor(this.requireContext(), R.color.light_red))
            binding.tvOptionBtnOld.setBackgroundResource(R.drawable.register_option_select)

            binding.tvOptionBtnNew.setTextColor(ContextCompat.getColor(this.requireContext(), R.color.gray))
            binding.tvOptionBtnNew.setBackgroundResource(R.drawable.register_option_unselect)
        }

        binding.tvOptionBtnNew.setOnClickListener {
            itemStatus = true
            binding.tvOptionBtnNew.setTextColor(ContextCompat.getColor(this.requireContext(), R.color.light_red))
            binding.tvOptionBtnNew.setBackgroundResource(R.drawable.register_option_select)

            binding.tvOptionBtnOld.setTextColor(ContextCompat.getColor(this.requireContext(), R.color.gray))
            binding.tvOptionBtnOld.setBackgroundResource(R.drawable.register_option_unselect)
        }

        binding.tvOptionBtnNo.setOnClickListener {
            itemexchange = false
            binding.tvOptionBtnNo.setTextColor(ContextCompat.getColor(this.requireContext(), R.color.light_red))
            binding.tvOptionBtnNo.setBackgroundResource(R.drawable.register_option_select)

            binding.tvOptionBtnYes.setTextColor(ContextCompat.getColor(this.requireContext(), R.color.gray))
            binding.tvOptionBtnYes.setBackgroundResource(R.drawable.register_option_unselect)
        }

        binding.tvOptionBtnYes.setOnClickListener {
            itemexchange = true
            binding.tvOptionBtnYes.setTextColor(ContextCompat.getColor(this.requireContext(), R.color.light_red))
            binding.tvOptionBtnYes.setBackgroundResource(R.drawable.register_option_select)

            binding.tvOptionBtnNo.setTextColor(ContextCompat.getColor(this.requireContext(), R.color.gray))
            binding.tvOptionBtnNo.setBackgroundResource(R.drawable.register_option_unselect)
        }

        binding.btnOptionChoice.setOnClickListener {
            itemCnt = if (binding.etOptionCount.text.toString() != "") {
                binding.etOptionCount.text.toString().toInt()
            } else {
                0
            }
            sendData(itemCnt, itemStatus, itemexchange)
            this.dismiss()
        }

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        optionPass = context as OnDataOption
    }

    fun sendData(itemCnt: Int, itemStatus: Boolean, itemexchange: Boolean){
        optionPass.onDataOption(itemCnt, itemStatus, itemexchange)
    }

    interface OnDataOption {
        fun onDataOption(itemCnt: Int, itemStatus: Boolean, itemexchange: Boolean)
    }
}