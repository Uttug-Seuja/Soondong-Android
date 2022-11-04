package com.junjange.soondong.repository

import android.app.Application
import android.util.Log
import com.google.gson.JsonObject
import com.junjange.soondong.data.ReservesSportDate
import com.junjange.soondong.data.User
import com.junjange.soondong.network.SoonDongObject

class MatchingTodayRepository(application : Application) {

    // singleton pattern
    companion object {
        private var instance: MatchingTodayRepository? = null

        fun getInstance(application : Application): MatchingTodayRepository? {
            if (instance == null) instance = MatchingTodayRepository(application)
            return instance
        }
    }

    // Use Retrofit
    suspend fun retrofitReservesSportToday(today: String): ReservesSportDate {
        val response = SoonDongObject.getRetrofitService.getReservesSportToday(today)
        return if (response.isSuccessful) response.body() as ReservesSportDate else ReservesSportDate(ArrayList())

    }
}