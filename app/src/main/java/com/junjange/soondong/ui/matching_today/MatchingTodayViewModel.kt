package com.junjange.soondong.ui.matching_today

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.junjange.soondong.data.ReservesCreation
import com.junjange.soondong.data.ReservesEdit
import com.junjange.soondong.data.ReservesSportDate
import com.junjange.soondong.data.User
import com.junjange.soondong.repository.MatchingTodayRepository
import com.junjange.soondong.repository.RegisterRepository
import kotlinx.coroutines.launch

class MatchingTodayViewModel(private val repository: MatchingTodayRepository) : ViewModel(){
    private val _retrofitReservesSportTodayText = MutableLiveData<ReservesSportDate>()


    val retrofitReservesSportTodayText: MutableLiveData<ReservesSportDate>
        get() = _retrofitReservesSportTodayText


    fun reservesSportTodayRetrofit(today: String) = viewModelScope.launch{
        _retrofitReservesSportTodayText.value = repository.retrofitReservesSportToday(today)

    }


    // factory pattern
    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MatchingTodayViewModel(MatchingTodayRepository.getInstance(application)!!) as T
        }
    }


}