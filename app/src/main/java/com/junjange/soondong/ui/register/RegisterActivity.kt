package com.junjange.soondong.ui.register

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.ViewModelProvider
import com.junjange.soondong.R
import com.junjange.soondong.databinding.ActivityRegisterBinding
import com.junjange.soondong.ui.main.MainActivity
import com.junjange.soondong.ui.signin.SigninActivity

class RegisterActivity : AppCompatActivity() {
    private val binding by lazy { ActivityRegisterBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this, RegisterViewModel.Factory(application))[RegisterViewModel::class.java] }
    private var userId : String? = null
    private var userPassword : String? = null
    private var userName : String? = null
    private var userStudentId : String? = null
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

        // 사용자 남자 성별 클릭
        binding.manBtn.setOnClickListener{

            userGender = true
            binding.manBtn.strokeColor = Color.parseColor("#007680")
            binding.womanBtn.strokeColor = Color.parseColor("#d9d9d9")
        }

        // 사용자 여자 성별 클릭
        binding.womanBtn.setOnClickListener {

            userGender = false
            binding.womanBtn.strokeColor = Color.parseColor("#007680")
            binding.manBtn.strokeColor = Color.parseColor("#d9d9d9")


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




        // 사용자 이름
        binding.nameEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {

                userName = binding.nameEditText.text.toString()


            }
        })


        // 사용자 학번
        binding.studentIdEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {

                userStudentId = binding.studentIdEditText.text.toString()


            }
        })
        binding.nextBtn.setOnClickListener {
            startActivity( Intent(this@RegisterActivity, SigninActivity::class.java))
            finish()

        }

    }
}