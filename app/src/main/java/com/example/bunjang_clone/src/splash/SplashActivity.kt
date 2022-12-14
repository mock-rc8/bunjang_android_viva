package com.example.bunjang_clone.src.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.bunjang_clone.config.BaseActivity
import com.example.bunjang_clone.databinding.ActivitySplashBinding
import com.example.bunjang_clone.src.MainActivity
import com.example.bunjang_clone.src.login.LoginActivity
import com.example.bunjang_clone.src.splash.models.SplashResponse

class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate), SplashActivityInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
//            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 1500)
        SplashService(this).getAutoLogin()
    }

    override fun onAutoLoginSuccess(response: SplashResponse) {
        when(response.code) {
            1000 -> {
                var intent = Intent(this, MainActivity::class.java)
                intent.putExtra("userIdx", response.result.userIdx)
                Log.d("userIdx", "${response.result.userIdx}")
                startActivity(intent)
            } else -> {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }

    override fun onAutoLoginFail(message: String) {
    }
}