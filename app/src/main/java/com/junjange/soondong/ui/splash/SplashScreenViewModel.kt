package com.junjange.soondong.ui.splash

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope

import com.junjange.soondong.repository.SplashScreenRepository
import kotlinx.coroutines.launch

class SplashScreenViewModel(private val repository: SplashScreenRepository) : ViewModel(){
//    private val _retrofitCheckFirstTestState = MutableLiveData<DataBoolean>()
//    private val _retrofitSignInText = MutableLiveData<DataString>()
//    private val _retrofitNicknameCurrentText = MutableLiveData<DataString>()
//
//    val retrofitCheckFirstTestState: MutableLiveData<DataBoolean>
//        get() = _retrofitCheckFirstTestState
//
//
//    val retrofitSignInText: MutableLiveData<DataString>
//        get() = _retrofitSignInText
//
//    val retrofitNicknameCurrentText: MutableLiveData<DataString>
//        get() = _retrofitNicknameCurrentText
//
//    init {
//        val email = MyApplication.prefs.getString("email", "no email")
//
//        viewModelScope.launch{
//            _retrofitSignInText.value = repository.retrofitSignIn(email)
////            _retrofitCheckFirstTestState.value = repository.retrofitCheckFirstTest()
////            _retrofitNicknameCurrentText.value = repository.retrofitNicknameCurrent()
//
//        }
//    }
//
//
//    fun checkFirstTestRetrofit() = viewModelScope.launch{
//        _retrofitCheckFirstTestState.value = repository.retrofitCheckFirstTest()
//
//    }
//
//
//    fun signInRetrofit(email : String) = viewModelScope.launch{
//        _retrofitSignInText.value = repository.retrofitSignIn(email)
//
//    }
//
//    fun retrofitNicknameCurrent() = viewModelScope.launch {
//        _retrofitNicknameCurrentText.value = repository.retrofitNicknameCurrent()
//
//    }
//
    // factory pattern
    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SplashScreenViewModel(SplashScreenRepository.getInstance(application)!!) as T
        }
    }


}