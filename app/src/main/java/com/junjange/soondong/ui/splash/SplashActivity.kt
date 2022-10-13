package com.junjange.soondong.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModelProvider
import com.junjange.soondong.R
import com.junjange.soondong.databinding.ActivitySplashBinding
import com.junjange.soondong.ui.main.MainActivity
import com.junjange.soondong.ui.register.RegisterActivity
import com.junjange.soondong.ui.signin.SigninActivity
import com.junjange.soondong.ui.signin.SigninViewModel
import com.junjange.soondong.utils.MyApplication

class SplashActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySplashBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.lifecycleOwner = this



        Handler(Looper.getMainLooper()).postDelayed({

            val memberId = MyApplication.prefs.getString("memberId", "")
            /**
             * 이미 로그인이 되어 있을 수도 있기 때문에 !!!!!!!!!!
             * 로그인을 해본다!!!!!!!!!
             * */
            if (memberId != ""){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

            }else{

                val intent = Intent(this, SigninActivity::class.java)
                startActivity(intent)
                finish()

            }

        }, 500)
    }

}