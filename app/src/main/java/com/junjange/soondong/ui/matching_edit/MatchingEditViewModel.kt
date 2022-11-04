package com.junjange.soondong.ui.matching_edit

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.junjange.soondong.data.ReservesCreation
import com.junjange.soondong.data.ReservesEdit
import com.junjange.soondong.data.ReservesSportDate
import com.junjange.soondong.repository.MatchingEditRepository
import com.junjange.soondong.repository.MatchingTodayRepository
import kotlinx.coroutines.launch

class MatchingEditViewModel(private val repository: MatchingEditRepository) : ViewModel(){
    private val _reservesCreationRetrofit = MutableLiveData<Boolean>()

    val reservesCreationRetrofit: MutableLiveData<Boolean>
        get() = _reservesCreationRetrofit

    fun reservesCreationRetrofit(reservesCreation: ReservesCreation) = viewModelScope.launch{
        _reservesCreationRetrofit.value = repository.retrofitReservesCreation(reservesCreation)

    }

    // factory pattern
    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MatchingEditViewModel(MatchingEditRepository.getInstance(application)!!) as T
        }
    }


}