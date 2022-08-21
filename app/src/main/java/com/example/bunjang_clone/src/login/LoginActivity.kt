package com.example.bunjang_clone.src.login

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.viewpager2.widget.ViewPager2
import com.example.bunjang_clone.R
import com.example.bunjang_clone.config.BaseActivity
import com.example.bunjang_clone.databinding.ActivityLoginBinding
import com.example.bunjang_clone.src.login.models.ViewPagerAd

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    var adList = ArrayList<ViewPagerAd>()

    private var currentPosition=0

    private lateinit var handler : Handler
    private lateinit var loginVpAdapter: LoginVpAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 로그인 광고 viewpager2 구현
        setView()

        // 뷰페이저 처음으로 옴기기
        handler = Handler(Looper.getMainLooper()){
            setPage()
            true
        }
        // 뷰페이저 자동 스레드
        var thread = Thread(PagerRunnable())
        thread.start()

    }
    fun setPage() {
        if(currentPosition== 4) {
            currentPosition = 0
        }
        binding.vpLoginAd.setCurrentItem(currentPosition, true)
        currentPosition++
    }

    fun setView() {
        adList.add(ViewPagerAd(R.drawable.viewpager_text1,
            getString(R.string.login_viewpager_text1), R.drawable.viewpager_img1))
        adList.add(ViewPagerAd(R.drawable.viewpager_text2,
            getString(R.string.login_viewpager_text2), R.drawable.viewpager_img2))
        adList.add(ViewPagerAd(R.drawable.viewpager_text3,
            getString(R.string.login_viewpager_text3), R.drawable.viewpager_img3))
        adList.add(ViewPagerAd(R.drawable.viewpager_text4,
            getString(R.string.login_viewpager_text4), R.drawable.viewpager_img4))

        loginVpAdapter = LoginVpAdapter(adList)
        binding.vpLoginAd.adapter = loginVpAdapter
        binding.vpLoginAd.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.vpAdIndicator.attachTo(binding.vpLoginAd)
    }

    inner class PagerRunnable:Runnable{
        override fun run() {
            while (true){
                Thread.sleep(5000)
                handler.sendEmptyMessage(0)
            }
        }
    }
}