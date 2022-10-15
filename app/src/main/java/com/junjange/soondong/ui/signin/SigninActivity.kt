package com.junjange.soondong.ui.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.junjange.soondong.R
import com.junjange.soondong.data.Login
import com.junjange.soondong.databinding.ActivityRegisterBinding
import com.junjange.soondong.databinding.ActivitySigninBinding
import com.junjange.soondong.ui.main.MainActivity
import com.junjange.soondong.ui.matching.MatchingActivity
import com.junjange.soondong.ui.register.RegisterActivity
import com.junjange.soondong.ui.register.RegisterViewModel
import com.junjange.soondong.utils.MyApplication

class SigninActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySigninBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this, SigninViewModel.Factory(application))[SigninViewModel::class.java] }

    private var userId : String? = null
    private var userPassword : String? = null
    private var memberId : String? = null
    private var userGender : Boolean? = null
    private var flag : Boolean = false
    private var googleEmail: String? = null


    private var userNameFlag : Boolean = false
    private var userBirthDayFlag : Boolean = false
    private var userGenderFlag : Boolean = false
    private var resultCheckFlag : Boolean = false

    private var totalFlag = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        binding.localSignInBt.setOnClickListener {

            startActivity( Intent(this@SigninActivity, RegisterActivity::class.java))
            finish()


        }




        // 사용자 아이디
        binding.idEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {

                userId = binding.idEditText.text.toString()


            }
        })

        // 사용자 비밀번호
        binding.passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {

                userPassword = binding.passwordEditText.text.toString()


            }
        })

        // 다음 버튼
        binding.loginBtn.setOnClickListener {

            userId = binding.idEditText.text.toString()
            userPassword = binding.passwordEditText.text.toString()
            Log.d("ttt",userNameFlag.toString())
            Log.d("ttt name",userId.toString())

//            registerLogic()
//            keyBordHide()
            binding.idEditText.clearFocus()
            binding.passwordEditText.clearFocus()

//            memberId = "-1" // 임시로 -1 지정
            MyApplication.prefs.setString("memberId", "")


            viewModel.signInRetrofit(Login(userId.toString(), userPassword.toString()))

            // 로그인 로직
            viewModel.retrofitSignInText.value.let {
                viewModel.retrofitSignInText.observe(this){
                    if (it.playerData.isNotEmpty()){
                        MyApplication.prefs.setString("memberId", memberId.toString())
                        startActivity( Intent(this@SigninActivity, MainActivity::class.java))
                        finish()
                    }
                }
            }


        }
    }
}