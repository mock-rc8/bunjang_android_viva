package com.example.bunjang_clone.src

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.bunjang_clone.R
import com.example.bunjang_clone.config.BaseActivity
import com.example.bunjang_clone.databinding.ActivityMainBinding
import com.example.bunjang_clone.src.home.HomeFragment
import com.example.bunjang_clone.src.mypage.MyPageFragment
import com.example.bunjang_clone.src.register.RegisterActivity
import com.example.bunjang_clone.src.register.RegisterFragment
import com.example.bunjang_clone.src.search.SearchFragment
import com.example.bunjang_clone.src.talk.TalkFragment

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var userId = intent.getIntExtra("userIdx", 0)
        Log.d("userIdx","$userId")

        supportFragmentManager.beginTransaction().add(R.id.fl_main_page, HomeFragment()).commit()

        binding.mainBtnNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.main_nav_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_main_page, HomeFragment())
                        .commit()
                }
                R.id.main_nav_search -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_main_page, SearchFragment())
                        .commit()
                }
                R.id.main_nav_register -> {
                    var intent = Intent(this, RegisterActivity::class.java)
                    intent.putExtra("userIdx", userId)
                    startActivity(intent)
                    return@setOnItemSelectedListener false
                }
                R.id.main_nav_talk -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_main_page, TalkFragment())
                        .commit()
                }
                else -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_main_page, MyPageFragment())
                        .commit()
                }
            }
            true
        }

    }
}