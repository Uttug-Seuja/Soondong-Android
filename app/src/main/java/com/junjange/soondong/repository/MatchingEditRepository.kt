package com.junjange.soondong.repository

import android.app.Application
import android.util.Log
import com.google.gson.JsonObject
import com.junjange.soondong.data.*
import com.junjange.soondong.network.SoonDongObject

class MatchingEditRepository (application : Application) {

    // singleton pattern
    companion object {
        private var instance: MatchingEditRepository? = null

        fun getInstance(application : Application): MatchingEditRepository? {
            if (instance == null) instance = MatchingEditRepository(application)
            return instance
        }
    }

    // Use Retrofit
    suspend fun retrofitReservesEdit(reservesEdit: ReservesEdit): Boolean {
        val response = SoonDongObject.getRetrofitService.postReservesEdit(reservesEdit)
        return response.isSuccessful

    }

    // Use Retrofit
    suspend fun retrofitReservesCreation(reservesCreation: ReservesCreation): Boolean {
        val response = SoonDongObject.getRetrofitService.postReservesCreation(reservesCreation)
        return response.isSuccessful
    }

    // 경기 정보
    suspend fun retrofitReservesInfo(reserveId: Int): ReservesInfo {
        val response = SoonDongObject.getRetrofitService.getReservesInfo(reserveId)
        return response.body() as ReservesInfo
    }
}