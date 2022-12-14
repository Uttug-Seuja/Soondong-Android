package com.junjange.soondong.repository

import android.app.Application
import android.util.Log
import com.google.gson.JsonObject
import com.junjange.soondong.data.Participation
import com.junjange.soondong.data.Player
import com.junjange.soondong.data.ReservesEdit
import com.junjange.soondong.data.ReservesInfo
import com.junjange.soondong.network.SoonDongObject

class MatchingDetailRepository (application : Application) {

    // singleton pattern
    companion object {
        private var instance: MatchingDetailRepository? = null

        fun getInstance(application : Application): MatchingDetailRepository? {
            if (instance == null) instance = MatchingDetailRepository(application)
            return instance
        }
    }

    // 경기 정보
    suspend fun retrofitReservesInfo(reserveId: Int): ReservesInfo {
        val response = SoonDongObject.getRetrofitService.getReservesInfo(reserveId)
        return response.body() as ReservesInfo
    }

    // 경기를 참여하는 사용자 정보들
    suspend fun retrofitParticipantUserInfo(reserveId: Int): Player {
        val response = SoonDongObject.getRetrofitService.getParticipantUserInfo(reserveId)
        return response.body() as Player
    }

    // 경기 삭제
    suspend fun retrofitDeleteReserves(reserveId: Int): Boolean {
        val response = SoonDongObject.getRetrofitService.deleteReserves(reserveId)
        return response.isSuccessful
    }

    // 경기 참여
    suspend fun retrofitPostParticipation(participation: Participation): JsonObject {
        val response = SoonDongObject.getRetrofitService.postParticipation(participation)
        return response.body() as JsonObject
    }

    // 경기 참여 취소
    suspend fun retrofitDeleteParticipation(participation: Participation): JsonObject {
        val response = SoonDongObject.getRetrofitService.deleteParticipation(participation)
        return response.body() as JsonObject
    }
}