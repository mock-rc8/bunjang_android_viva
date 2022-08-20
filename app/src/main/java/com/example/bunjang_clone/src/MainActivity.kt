package com.example.bunjang_clone.src

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.bunjang_clone.R
import com.example.bunjang_clone.config.BaseActivity
import com.example.bunjang_clone.databinding.ActivityMainBinding
import com.example.bunjang_clone.src.home.HomeFragment
import com.example.bunjang_clone.src.mypage.MyPageFragment
import com.example.bunjang_clone.src.register.RegisterFragment
import com.example.bunjang_clone.src.search.SearchFragment
import com.example.bunjang_clone.src.talk.TalkFragment

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().add(R.id.fl_main_page, HomeFragment()).commit()

        binding.mainBtnNav.setOnItemSelectedListener {
            replaceFragment(
                when(it.itemId){
                    R.id.main_nav_home -> HomeFragment()
                    R.id.main_nav_search -> SearchFragment()
                    R.id.main_nav_register -> RegisterFragment()
                    R.id.main_nav_talk -> TalkFragment()
                    else -> MyPageFragment()
                }
            )
            true
        }


    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fl_main_page, fragment).commit()
    }
}