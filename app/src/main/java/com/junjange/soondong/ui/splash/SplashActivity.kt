package com.junjange.soondong.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModelProvider
import com.junjange.soondong.R
import com.junjange.soondong.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySplashBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this, SplashScreenViewModel.Factory(application))[SplashScreenViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        Handler(Looper.getMainLooper()).postDelayed({

            /**
             * 이미 로그인이 되어 있을 수도 있기 때문에 !!!!!!!!!!
             * 로그인을 해본다!!!!!!!!!
             * */

//            viewModel.retrofitSignInText.value.let { DataString ->
//                // 로그인이 되어 있따면
//                if (DataString!!.dataString.toString() != "null"){
//                    MyApplication.prefs.setString("token", "Bearer ${DataString.dataString.toString()}") // 토큰 저장
//                    viewModel.checkFirstTestRetrofit() // 사용자가 첫 번째 검사를 했는지 확인
//                    viewModel.retrofitCheckFirstTestState.observe(this){
//                        viewModel.retrofitCheckFirstTestState.value.let { DataBoolean ->
//                            viewModel.retrofitNicknameCurrent() // 사용자 닉네임 확인
//                            viewModel.retrofitNicknameCurrentText.observe(this){it ->
//                                viewModel.retrofitNicknameCurrentText.value.let {
//                                    // 닉네임 저장
//                                    MyApplication.prefs.setString("nickName", it!!.dataString.toString())
//
//                                    // 사용자가 첫 번째 검사를 했다면 MainActivity 이동
//                                    if (DataBoolean!!.dataBoolean == true){
//                                        val intent = Intent(this, MainActivity::class.java)
//                                        startActivity(intent)
//                                        finish()
//
//                                        // 사용자가 첫 번째 검사를 안 했다면 Splash1Activity 이동
//                                    }else{
//                                        val intent = Intent(this, Splash1Activity::class.java)
//                                        startActivity(intent)
//                                        finish()
//                                    }
//                                }
//                            }
//                        }
//                    }
//
//                    // 로그인이 되어 있지 않다면 회원가입하러 RegisterActivity 이동
//                }else{
//                    val intent = Intent(this, RegisterActivity::class.java)
//                    startActivity(intent)
//                    finish()
//                }
//            }
        }, 500)
    }

}