package com.example.bunjang_clone.src.login

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.example.bunjang_clone.R
import com.example.bunjang_clone.config.BaseActivity
import com.example.bunjang_clone.databinding.ActivityLoginPhoneBinding
import com.example.bunjang_clone.src.login.models.LoginAgencyData
import com.example.bunjang_clone.src.login.models.LoginAgreeData
import com.example.bunjang_clone.src.login.models.ViewPagerAd
import com.google.android.material.bottomsheet.BottomSheetDialog

class PhoneLoginActivity : BaseActivity<ActivityLoginPhoneBinding>(ActivityLoginPhoneBinding::inflate),
    View.OnClickListener {

    private var name = false
    private var birthday = false
    private var sex = false
    private var agency = false
    private var phone = false
    private var password = false

    var AgencyList = ArrayList<LoginAgencyData>()
    var AgreeList = ArrayList<LoginAgreeData>()

    private lateinit var agencyAdapter: LoginAgencyRvAdapter
    private lateinit var agencyDialog : BottomSheetDialog

    private lateinit var agreeAdapter: LoginAgreeRvAdapter
    private lateinit var agreeDialog : BottomSheetDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnLoginNext.setOnClickListener(this)
        checkName()
        setAgency()
        setAgree()

        binding.ivPhoneBack.setOnClickListener {
            finish()
        }
    }
    private fun setAgency() {
        AgencyList.add(LoginAgencyData("SKT", R.drawable.icon_radio_unclick ))
        AgencyList.add(LoginAgencyData("KT", R.drawable.icon_radio_unclick ))
        AgencyList.add(LoginAgencyData("LG U+", R.drawable.icon_radio_unclick ))
        AgencyList.add(LoginAgencyData("SKT 알뜰폰", R.drawable.icon_radio_unclick ))
        AgencyList.add(LoginAgencyData("KT 알뜰폰", R.drawable.icon_radio_unclick ))
        AgencyList.add(LoginAgencyData("LG U+ 알뜰폰", R.drawable.icon_radio_unclick ))

        val agencyView = layoutInflater.inflate(R.layout.dialog_login_agency, null)
        agencyDialog = BottomSheetDialog(this)
        agencyDialog.setContentView(agencyView)

        agencyAdapter = LoginAgencyRvAdapter(this, AgencyList)

        // 리사이클러뷰 연결
        val agencyRv = agencyView.findViewById<RecyclerView>(R.id.rv_login_agency)
        agencyRv.adapter = agencyAdapter

        binding.llLoginPhoneAgency.setOnClickListener {
            agencyDialog.show()
        }

        agencyAdapter.clickListener(object : LoginAgencyRvAdapter.OnItemClickListener {
            override fun onClick(view: View, position: Int) {
                binding.tvLoginPhoneAgency.text = agencyAdapter.itemList[position].title
                agencyAdapter.notifyDataSetChanged()
                agencyDialog.dismiss()

                agency = true
                checkPhoneNumber()
            }

        })
    }
    private fun setAgree() {
        AgreeList.add(LoginAgreeData("번개장터 이용약관 (필수)", false ))
        AgreeList.add(LoginAgreeData("개인정보 수집 이용 동의 (필수)", false ))
        AgreeList.add(LoginAgreeData("휴대폰 본인확인서비스 (필수)", false))
        AgreeList.add(LoginAgreeData("휴면시 개인정보 분리보관 동의 (필수)", false ))
        AgreeList.add(LoginAgreeData("위치정보 이용약관 동의 (필수)", false ))
        AgreeList.add(LoginAgreeData("개인정보 수집 이용 동의 (선택)", false ))
        AgreeList.add(LoginAgreeData("마케팅 수신 동의 (선택)", false ))
        AgreeList.add(LoginAgreeData("개인정보 광고활용 동의 (선택)", false ))

        val agreeView = layoutInflater.inflate(R.layout.dialog_login_agreement, null)
        agreeDialog = BottomSheetDialog(this)
        agreeDialog.setContentView(agreeView)

        agreeAdapter = LoginAgreeRvAdapter(this, AgreeList)

        // 리사이클러뷰 연결
        val agreeRv = agreeView.findViewById<RecyclerView>(R.id.rv_dialog_agree_list)
        agreeRv.adapter = agreeAdapter

        agreeAdapter.clickListener(object : LoginAgreeRvAdapter.OnItemClickListener {
            override fun onClick(view: View, position: Int) {
                agreeAdapter.notifyDataSetChanged()
                agencyDialog.dismiss()
            }

        })


    }

    // 이름
    private fun checkName(){
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
        binding.etLoginBirthday.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.etLoginBirthday.length() == 6){
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
                binding.tvLoginTitle.text = "입력한 정보를\n확인해주세요"
            }

        })
    }

    override fun onClick(v: View?) {
        when(v!!.id) {
            R.id.btn_login_next -> {
                if (name && !birthday) {
                    binding.llLoginBirthday.visibility = View.VISIBLE
                    binding.etLoginBirthday.requestFocus()
                    binding.tvLoginTitle.text = "생년월일\n입력해주세요"
                    binding.btnLoginNext.text = "확인"
                    binding.viewName.setBackgroundColor(this.resources.getColor(R.color.more_light_gray))
                    binding.btnLoginNext.isEnabled = false
                    checkBirthday()
                }
                if (birthday && sex && !agency) {
                    binding.viewBirthday1.setBackgroundColor(this.resources.getColor(R.color.more_light_gray))
                    binding.viewBirthday2.setBackgroundColor(this.resources.getColor(R.color.more_light_gray))
                    binding.btnLoginNext.text = "확인"
                    binding.btnLoginNext.isEnabled = false
                    agency()
                }
                if (agency && !phone){
                    binding.viewPhone.setBackgroundColor(this.resources.getColor(R.color.more_light_gray))
                    binding.btnLoginNext.text = "확인"
                    binding.btnLoginNext.isEnabled = false
                    checkPhoneNumber()
                }
                if (phone && !password){
                    agreeDialog.show()
                }
            }
        }
    }
}