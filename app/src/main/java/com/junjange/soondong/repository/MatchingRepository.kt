package com.junjange.soondong.repository

import android.app.Application
import android.util.Log
import com.junjange.soondong.data.ReservesSportDate
import com.junjange.soondong.network.SoonDongObject

class MatchingRepository (application : Application) {


    // singleton pattern
    companion object {
        private var instance: MatchingRepository? = null

        fun getInstance(application : Application): MatchingRepository? {
            if (instance == null) instance = MatchingRepository(application)
            return instance
        }
    }

    // Use Retrofit
    suspend fun retrofitReservesSportDate(sport: String, today: String): ReservesSportDate {
        val response = SoonDongObject.getRetrofitService.getReservesSportDate(sport, "2000-01-03")
        Log.d("ttt", today.toString())

        Log.d("tttresponse.body()", response.body().toString())
        Log.d("tttresponse", response.toString())


        return if (response.isSuccessful) response.body() as ReservesSportDate else ReservesSportDate(ArrayList())

    }



}