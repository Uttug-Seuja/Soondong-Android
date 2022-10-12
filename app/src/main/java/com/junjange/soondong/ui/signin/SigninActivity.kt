package com.junjange.soondong.ui.signin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.junjange.soondong.R
import com.junjange.soondong.databinding.ActivityRegisterBinding
import com.junjange.soondong.databinding.ActivitySigninBinding
import com.junjange.soondong.ui.register.RegisterViewModel

class SigninActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySigninBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this, SigninViewModel.Factory(application))[SigninViewModel::class.java] }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }
}