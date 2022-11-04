package com.junjange.soondong.ui.signin

import android.app.Application
import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.junjange.soondong.data.*
import com.junjange.soondong.repository.RegisterRepository
import com.junjange.soondong.repository.SigninRepository
import kotlinx.coroutines.launch

class SigninViewModel(private val repository: SigninRepository) : ViewModel(){
    private val _retrofitSignInText = MutableLiveData<DataInt>()

    val retrofitSignInText: MutableLiveData<DataInt>
        get() = _retrofitSignInText

    fun signInRetrofit(login : Login) = viewModelScope.launch{
        _retrofitSignInText.value = repository.retrofitSignIn(login)

    }

    // factory pattern
    class Factory(val application: Application) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SigninViewModel(SigninRepository.getInstance(application)!!) as T
        }
    }
}