package com.junjange.soondong.ui.matching_update

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.junjange.soondong.data.ReservesEdit
import com.junjange.soondong.repository.MatchingEditRepository
import kotlinx.coroutines.launch

class MatchingUpdateViewModel (private val repository: MatchingEditRepository) : ViewModel(){
    private val _retrofitReservesEditText = MutableLiveData<Boolean>()

    val retrofitReservesEditText: MutableLiveData<Boolean>
        get() = _retrofitReservesEditText

    fun reservesEditRetrofit(reservesEdit: ReservesEdit) = viewModelScope.launch{
        _retrofitReservesEditText.value = repository.retrofitReservesEdit(reservesEdit)

    }

    // factory pattern
    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MatchingUpdateViewModel(MatchingEditRepository.getInstance(application)!!) as T
        }
    }


}