package com.junjange.soondong.ui.signin

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.junjange.soondong.repository.RegisterRepository

class SigninViewModel(private val repository: RegisterRepository) : ViewModel(){
//    private val _retrofitNicknameExistsState = MutableLiveData<DataBoolean>()
//    private val _retrofitCheckFirstTestState = MutableLiveData<DataBoolean>()
//    private val _retrofitSignUpText = MutableLiveData<DataInt>()
//    private val _retrofitSignInText = MutableLiveData<DataString>()
//    private val _retrofitNicknameCurrentText = MutableLiveData<DataString>()
//
//    val retrofitNicknameExistsState: MutableLiveData<DataBoolean>
//        get() = _retrofitNicknameExistsState
//
//    val retrofitCheckFirstTestState: MutableLiveData<DataBoolean>
//        get() = _retrofitCheckFirstTestState
//
//    val retrofitSignUpText: MutableLiveData<DataInt>
//        get() = _retrofitSignUpText
//
//    val retrofitSignInText: MutableLiveData<DataString>
//        get() = _retrofitSignInText
//
//
//    val retrofitNicknameCurrentText: MutableLiveData<DataString>
//        get() = _retrofitNicknameCurrentText
//
//
//
//    fun retrofitNicknameCurrent() = viewModelScope.launch {
//        _retrofitNicknameCurrentText.value = repository.retrofitNicknameCurrent()
//
//    }
//
//
//    fun nicknameExistsRetrofit(nickName : String) = viewModelScope.launch{
//        _retrofitNicknameExistsState.value = repository.retrofitNicknameExists(nickName)
//
//    }
//
//    fun checkFirstTestRetrofit() = viewModelScope.launch{
//        _retrofitCheckFirstTestState.value = repository.retrofitCheckFirstTest()
//
//    }
//
//    fun signUpRetrofit(user : User) = viewModelScope.launch{
//        _retrofitSignUpText.value = repository.retrofitSignUp(user)
//
//    }
//
//    fun signInRetrofit(email : String) = viewModelScope.launch{
//        _retrofitSignInText.value = repository.retrofitSignIn(email)
//
//    }
//
//
    // factory pattern
    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SigninViewModel(RegisterRepository.getInstance(application)!!) as T
        }
    }


}