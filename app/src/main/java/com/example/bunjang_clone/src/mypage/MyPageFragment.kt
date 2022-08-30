package com.example.bunjang_clone.src.mypage

import android.app.Application
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import com.example.bunjang_clone.R
import com.example.bunjang_clone.config.ApplicationClass
import com.example.bunjang_clone.config.ApplicationClass.Companion.sSharedPreferences
import com.example.bunjang_clone.config.BaseFragment
import com.example.bunjang_clone.databinding.FragmentMypageBinding
import com.example.bunjang_clone.src.login.LoginActivity

class MyPageFragment : BaseFragment<FragmentMypageBinding>(FragmentMypageBinding::bind, R.layout.fragment_mypage) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogout.setOnClickListener {
            val editor = ApplicationClass.sSharedPreferences.edit()
            editor.remove("X-ACCESS-TOKEN")
            editor.commit()
            startActivity(Intent(activity, LoginActivity::class.java))
            activity?.finish()
        }
    }
}