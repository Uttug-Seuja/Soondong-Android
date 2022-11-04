package com.junjange.soondong.ui.matching_detail

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.junjange.soondong.data.Participation
import com.junjange.soondong.data.Player
import com.junjange.soondong.data.ReservesInfo
import com.junjange.soondong.repository.MatchingDetailRepository
import kotlinx.coroutines.launch

class MatchingDetailViewModel(private val repository: MatchingDetailRepository) : ViewModel(){
    private val _retrofitReservesInfoText = MutableLiveData<ReservesInfo>()
    private val _retrofitParticipantUserInfoText = MutableLiveData<Player>()
    private val _retrofitDeleteReservesText = MutableLiveData<Boolean>()
    private val _retrofitPostParticipationText = MutableLiveData<JsonObject>()
    private val _retrofitDeleteParticipationText = MutableLiveData<JsonObject>()

    val retrofitReservesInfoText: MutableLiveData<ReservesInfo>
        get() = _retrofitReservesInfoText

    val retrofitParticipantUserInfoText: MutableLiveData<Player>
        get() = _retrofitParticipantUserInfoText

    val retrofitDeleteReservesText: MutableLiveData<Boolean>
        get() = _retrofitDeleteReservesText

    // 경기 정보
    fun reservesInfoRetrofit(reserveId: Int) = viewModelScope.launch{
        _retrofitReservesInfoText.value = repository.retrofitReservesInfo(reserveId)

    }
    // 경기를 참여하는 사용자 정보들
    fun participantUserInfoRetrofit(reserveId: Int) = viewModelScope.launch{
        _retrofitParticipantUserInfoText.value = repository.retrofitParticipantUserInfo(reserveId)

    }
    // 경기 삭제
    fun deleteReservesRetrofit(reserveId: Int) = viewModelScope.launch{
        _retrofitDeleteReservesText.value = repository.retrofitDeleteReserves(reserveId)
    }

    // 경기 참여
    fun postParticipationRetrofit(participation: Participation) = viewModelScope.launch{
        _retrofitPostParticipationText.value = repository.retrofitPostParticipation(participation)
        _retrofitReservesInfoText.value = repository.retrofitReservesInfo(participation.reserveId)
        _retrofitParticipantUserInfoText.value = repository.retrofitParticipantUserInfo(participation.reserveId)

    }

    // 경기 참여 취소
    fun deleteParticipationRetrofit(participation: Participation) = viewModelScope.launch{
        _retrofitDeleteParticipationText.value = repository.retrofitDeleteParticipation(participation)
        _retrofitReservesInfoText.value = repository.retrofitReservesInfo(participation.reserveId)
        _retrofitParticipantUserInfoText.value = repository.retrofitParticipantUserInfo(participation.reserveId)

    }

    // factory pattern
    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MatchingDetailViewModel(MatchingDetailRepository.getInstance(application)!!) as T
        }
    }


}