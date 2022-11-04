package com.junjange.soondong.ui.matching_update

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.junjange.soondong.data.ReservesEdit
import com.junjange.soondong.data.ReservesInfo
import com.junjange.soondong.repository.MatchingEditRepository
import kotlinx.coroutines.launch

class MatchingUpdateViewModel (private val repository: MatchingEditRepository) : ViewModel(){
    private val _retrofitReservesEditText = MutableLiveData<Boolean>()
    private val _retrofitReservesInfoText = MutableLiveData<ReservesInfo>()

    val retrofitReservesEditText: MutableLiveData<Boolean>
        get() = _retrofitReservesEditText
    val retrofitReservesInfoText: MutableLiveData<ReservesInfo>
        get() = _retrofitReservesInfoText

    fun reservesEditRetrofit(reservesEdit: ReservesEdit) = viewModelScope.launch{
        _retrofitReservesEditText.value = repository.retrofitReservesEdit(reservesEdit)

    }

    // 경기 정보
    fun reservesInfoRetrofit(reserveId: Int) = viewModelScope.launch{
        _retrofitReservesInfoText.value = repository.retrofitReservesInfo(reserveId)

    }

    // factory pattern
    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MatchingUpdateViewModel(MatchingEditRepository.getInstance(application)!!) as T
        }
    }


}