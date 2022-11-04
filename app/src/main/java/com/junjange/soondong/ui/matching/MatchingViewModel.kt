package com.junjange.soondong.ui.matching

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.junjange.soondong.data.ReservesInfo
import com.junjange.soondong.data.ReservesSportDate
import com.junjange.soondong.repository.MatchingDetailRepository
import com.junjange.soondong.repository.MatchingRepository
import kotlinx.coroutines.launch

class MatchingViewModel(private val repository: MatchingRepository) : ViewModel(){
    private val _reservesSportDateText = MutableLiveData<ReservesSportDate>()

    val reservesSportDateText: MutableLiveData<ReservesSportDate>
        get() = _reservesSportDateText


    fun retrofitReservesInfoRetrofit(sport: String, today: String) = viewModelScope.launch{
        _reservesSportDateText.value = repository.retrofitReservesSportDate(sport, today)

    }
    // factory pattern
    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MatchingViewModel(MatchingRepository.getInstance(application)!!) as T
        }
    }


}