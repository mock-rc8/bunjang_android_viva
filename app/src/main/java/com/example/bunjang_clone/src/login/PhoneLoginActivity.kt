package com.example.bunjang_clone.src.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.bunjang_clone.R
import com.example.bunjang_clone.config.ApplicationClass
import com.example.bunjang_clone.config.BaseActivity
import com.example.bunjang_clone.databinding.ActivityLoginPhoneBinding
import com.example.bunjang_clone.src.MainActivity
import com.example.bunjang_clone.src.login.adapter.LoginAgencyRvAdapter
import com.example.bunjang_clone.src.login.adapter.LoginAgreeRvAdapter
import com.example.bunjang_clone.src.login.models.LoginAgencyData
import com.example.bunjang_clone.src.login.models.LoginAgreeData
import com.example.bunjang_clone.src.login.models.LoginResponse
import com.example.bunjang_clone.src.login.models.SignUpResponse
import com.google.android.material.bottomsheet.BottomSheetDialog

class PhoneLoginActivity :
    BaseActivity<ActivityLoginPhoneBinding>(ActivityLoginPhoneBinding::inflate),
    View.OnClickListener, LoginActivityInterface {

    private var name = false
    private var birthday = false
    private var sex = false
    private var agency = false
    private var phone = false
    private var agree = false
    private var password = false
    private var shopName = false
    private var allclick = false

    var AgencyList = ArrayList<LoginAgencyData>()
    var AgreeList = ArrayList<LoginAgreeData>()

    private lateinit var agencyAdapter: LoginAgencyRvAdapter
    private lateinit var agencyDialog: BottomSheetDialog

    private lateinit var agreeAdapter: LoginAgreeRvAdapter
    private lateinit var agreeDialog: BottomSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 확인버튼은 선택
        binding.btnLoginNext.setOnClickListener(this)

        //시작 이름 메소도
        checkName()

        // 통신사 선택
        setAgency()

        // 약관 동의
        setAgree()
        
        // 뒤로돌아가기
        binding.ivPhoneBack.setOnClickListener {
            finish()
        }
    }

    private fun setAgency() {
        AgencyList.add(LoginAgencyData("SKT", R.drawable.icon_radio_unclick, false))
        AgencyList.add(LoginAgencyData("KT", R.drawable.icon_radio_unclick, false))
        AgencyList.add(LoginAgencyData("LG U+", R.drawable.icon_radio_unclick, false))
        AgencyList.add(LoginAgencyData("SKT 알뜰폰", R.drawable.icon_radio_unclick, false))
        AgencyList.add(LoginAgencyData("KT 알뜰폰", R.drawable.icon_radio_unclick, false))
        AgencyList.add(LoginAgencyData("LG U+ 알뜰폰", R.drawable.icon_radio_unclick, false))

        val agencyView = layoutInflater.inflate(R.layout.dialog_login_agency, null)
        agencyDialog = BottomSheetDialog(this)
        agencyDialog.setContentView(agencyView)

        agencyAdapter = LoginAgencyRvAdapter(this, AgencyList)

        agencyAdapter.setAgencyItem(AgencyList)

        // 리사이클러뷰 연결
        val agencyRv = agencyView.findViewById<RecyclerView>(R.id.rv_login_agency)
        agencyRv.adapter = agencyAdapter

        binding.llLoginPhoneAgency.setOnClickListener {
            agencyDialog.show()
        }

        agencyAdapter.clickListener(object : LoginAgencyRvAdapter.OnItemClickListener {
            override fun onClick(view: View, position: Int) {
                binding.tvLoginPhoneAgency.text = agencyAdapter.itemList[position].title
                for (i in 0 until AgencyList.size) {
                    agencyAdapter.itemList[i].click = false
                }
                agencyAdapter.itemList[position].click = true
                agencyAdapter.notifyDataSetChanged()
                agencyDialog.dismiss()
                agency = true
                checkPhoneNumber()
            }
        })
    }

    private fun setAgree() {
        AgreeList.add(LoginAgreeData("번개장터 이용약관 (필수)", false))
        AgreeList.add(LoginAgreeData("개인정보 수집 이용 동의 (필수)", false))
        AgreeList.add(LoginAgreeData("휴대폰 본인확인서비스 (필수)", false))
        AgreeList.add(LoginAgreeData("휴면시 개인정보 분리보관 동의 (필수)", false))
        AgreeList.add(LoginAgreeData("위치정보 이용약관 동의 (필수)", false))
        AgreeList.add(LoginAgreeData("개인정보 수집 이용 동의 (선택)", false))
        AgreeList.add(LoginAgreeData("마케팅 수신 동의 (선택)", false))
        AgreeList.add(LoginAgreeData("개인정보 광고활용 동의 (선택)", false))

        val agreeView = layoutInflater.inflate(R.layout.dialog_login_agreement, null)
        agreeDialog = BottomSheetDialog(this)
        agreeDialog.setContentView(agreeView)

        agreeAdapter = LoginAgreeRvAdapter(this, AgreeList)

        agreeAdapter.setAgencyItem(AgreeList)

        // 리사이클러뷰 연결
        val agreeRv = agreeView.findViewById<RecyclerView>(R.id.rv_dialog_agree_list)
        agreeRv.adapter = agreeAdapter

        agreeAdapter.clickListener(object : LoginAgreeRvAdapter.OnItemClickListener {
            override fun onClick(view: View, position: Int) {
                agreeAdapter.agreeList[position].isCheck = !agreeAdapter.agreeList[position].isCheck
                agreeAdapter.notifyDataSetChanged()
                if (agreeAdapter.agreeList[0].isCheck && agreeAdapter.agreeList[1].isCheck && agreeAdapter.agreeList[2].isCheck &&
                    agreeAdapter.agreeList[3].isCheck && agreeAdapter.agreeList[4].isCheck) {
                    agreeView.findViewById<AppCompatButton>(R.id.btn_dialog_next).isClickable = true
                    agreeView.findViewById<AppCompatButton>(R.id.btn_dialog_next).setOnClickListener {
                        agree = true
                        agreeDialog.dismiss()
                    }
                } else {
                    agreeView.findViewById<AppCompatButton>(R.id.btn_dialog_next).isClickable = false
                }
            }
        })
        agreeView.findViewById<ConstraintLayout>(R.id.cl_dialog_all_agree).setOnClickListener {
            if (allclick) {
                agreeView.findViewById<ImageView>(R.id.iv_dialog_all_check).setImageResource(R.drawable.icon_all_unagree)
                for (i in 0 until AgreeList.size){
                    agreeAdapter.agreeList[i].isCheck = false
                }
                agreeAdapter.notifyDataSetChanged()
                if (agreeAdapter.agreeList[0].isCheck && agreeAdapter.agreeList[1].isCheck && agreeAdapter.agreeList[2].isCheck &&
                    agreeAdapter.agreeList[3].isCheck && agreeAdapter.agreeList[4].isCheck) {
                    agreeView.findViewById<AppCompatButton>(R.id.btn_dialog_next).isClickable = true
                    agreeView.findViewById<AppCompatButton>(R.id.btn_dialog_next).setOnClickListener {
                        agree = true
                        agreeDialog.dismiss()
                    }
                }
                allclick = false
            } else {
                agreeView.findViewById<ImageView>(R.id.iv_dialog_all_check).setImageResource(R.drawable.icon_all_agree)
                for (i in 0 until AgreeList.size){
                    agreeAdapter.agreeList[i].isCheck = true
                }
                agreeAdapter.notifyDataSetChanged()
                if (agreeAdapter.agreeList[0].isCheck && agreeAdapter.agreeList[1].isCheck && agreeAdapter.agreeList[2].isCheck &&
                    agreeAdapter.agreeList[3].isCheck && agreeAdapter.agreeList[4].isCheck) {
                    agreeView.findViewById<AppCompatButton>(R.id.btn_dialog_next).isClickable = true
                    agreeView.findViewById<AppCompatButton>(R.id.btn_dialog_next).setOnClickListener {
                        agree = true
                        agreeDialog.dismiss()
                    }
                }
                allclick = true
            }
        }
    }

    // 이름
    private fun checkName() {
        binding.etLoginName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                binding.btnLoginNext.isEnabled = binding.etLoginName.length() >= 1
                name = true

            }
        })
    }

    // 생일
    private fun checkBirthday() {
        binding.etLoginBirthday.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.etLoginBirthday.length() == 6) {
                    binding.etLoginSex.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {
                birthday = true
                checkSex()
            }

        })
    }

    // 성별
    fun checkSex() {
        binding.etLoginSex.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                binding.btnLoginNext.isEnabled = binding.etLoginSex.length() > 0
                sex = true
            }

        })
    }

    // 통신사
    private fun agency() {
        binding.llLoginPhoneAgency.visibility = View.VISIBLE
        binding.tvLoginTitle.text = "통신사\n선택해주세요"

        agencyDialog.show()
    }

    //핸드폰 번호
    private fun checkPhoneNumber() {
        binding.llLoginPhoneNumber.visibility = View.VISIBLE
        binding.etLoginPhoneNumber.requestFocus()
        binding.etLoginPhoneNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                binding.btnLoginNext.isEnabled = binding.etLoginPhoneNumber.length() > 10
                phone = true
            }

        })
    }
    private fun passWord() {
        binding.llLoginName.visibility = View.GONE
        binding.llLoginBirthday.visibility = View.GONE
        binding.llLoginPhoneAgency.visibility = View.GONE
        binding.llLoginPhoneNumber.visibility = View.GONE
        binding.llLoginPassword.visibility = View.VISIBLE

        binding.tvLoginTitle.text = "비밀번호를\n입력해주세요"

        binding.etLoginPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                binding.btnLoginNext.isEnabled = binding.etLoginPassword.length() > 3
                password = true
            }

        })

    }
    private fun shopName() {
        binding.llLoginPassword.visibility = View.GONE
        binding.llLoginShopName.visibility = View.VISIBLE

        binding.tvLoginTitle.text = "마지막 단계입니다!\n상점명을 입력해주세요"

        binding.etLoginShopName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s!!.isNotEmpty()){
                    shopName = true
                    binding.btnLoginNext.isEnabled = true
                }
            }

        })
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_login_next -> {
                if (name && !birthday) {
                    binding.llLoginBirthday.visibility = View.VISIBLE
                    binding.etLoginBirthday.requestFocus()
                    binding.tvLoginTitle.text = "생년월일\n입력해주세요"
                    binding.btnLoginNext.text = "확인"
                    binding.viewName.setBackgroundColor(ContextCompat.getColor(this, R.color.more_light_gray))
                    binding.btnLoginNext.isEnabled = false
                    checkBirthday()
                }
                if (birthday && sex && !agency) {
                    binding.viewBirthday1.setBackgroundColor(ContextCompat.getColor(this, R.color.more_light_gray))
                    binding.viewBirthday2.setBackgroundColor(ContextCompat.getColor(this, R.color.more_light_gray))
                    binding.btnLoginNext.text = "확인"
                    binding.btnLoginNext.isEnabled = false
                    agency()
                }
                if (agency && !phone) {
                    binding.viewPhone.setBackgroundColor(ContextCompat.getColor(this, R.color.more_light_gray))
                    binding.btnLoginNext.text = "확인"
                    binding.btnLoginNext.isEnabled = false
                    checkPhoneNumber()
                }
                if (phone && !agree) {
                    agreeDialog.show()
                }
                if (agree && !password) {
                    binding.viewPhoneNumber.setBackgroundColor(ContextCompat.getColor(this, R.color.more_light_gray))
                    binding.btnLoginNext.text = "확인"
                    binding.btnLoginNext.isEnabled = false
                    passWord()
                }
                if(password && !shopName){
                    binding.btnLoginNext.text = "확인"
                    binding.btnLoginNext.isEnabled = false
                    tryLogin()
                }
                if (password && shopName){
                    trySignUp()
                }
            }
        }
    }
    fun trySignUp() {
        LoginService(this).SignUp(
            binding.etLoginName.text.toString(),
            binding.etLoginSex.text.toString(),
            binding.etLoginBirthday.text.toString(),
            binding.tvLoginPhoneAgency.text.toString(),
            binding.etLoginPhoneNumber.text.toString(),
            binding.etLoginPassword.text.toString(),
            binding.etLoginShopName.text.toString()
        )
    }
    fun tryLogin() {
        LoginService(this).Login(
            binding.etLoginName.text.toString(),
            binding.etLoginSex.text.toString(),
            binding.etLoginBirthday.text.toString(),
            binding.tvLoginPhoneAgency.text.toString(),
            binding.etLoginPhoneNumber.text.toString(),
            binding.etLoginPassword.text.toString()
        )
    }

    override fun onSingUpSuccess(response : SignUpResponse) {
        if (response.isSuccess){
            Log.d("jwt확인", "SingUp : ${response.result.jwt}")
            ApplicationClass.sSharedPreferences.edit().putString("Bunjang", response.result.jwt).apply()
            Log.d("jwt확인", "SingUp : ${response.code}")
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
    override fun onSingUpFail(message: String) {

    }
    override fun onLoginSuccess(response: LoginResponse) {

        if(response.isSuccess){
            Log.d("jwt확인", "SingUp : ${response.result.jwt}")
            ApplicationClass.sSharedPreferences.edit().putString("Bunjang", response.result.jwt).apply()
            Log.d("jwt확인", "SingUp : ${response.code}")
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            if (response.code == 2021)
                shopName()
        }
    }
    override fun onLoginFail(message: String) {
    }
}