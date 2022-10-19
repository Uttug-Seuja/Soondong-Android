package com.junjange.soondong.ui.register

import android.app.Application
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.junjange.soondong.data.User
import com.junjange.soondong.repository.RegisterRepository
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: RegisterRepository) : ViewModel(){
    private val _retrofitSignUpText = MutableLiveData<Boolean>()


    val retrofitSignUpText: MutableLiveData<Boolean>
        get() = _retrofitSignUpText


    fun signUpRetrofit(user : User) = viewModelScope.launch{
        _retrofitSignUpText.value = repository.retrofitSignUp(user)

    }


    // factory pattern
    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return RegisterViewModel(RegisterRepository.getInstance(application)!!) as T
        }
    }


}