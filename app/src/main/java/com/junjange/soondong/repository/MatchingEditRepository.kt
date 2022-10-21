package com.junjange.soondong.repository

import android.app.Application
import android.util.Log
import com.google.gson.JsonObject
import com.junjange.soondong.data.ReservesCreation
import com.junjange.soondong.data.ReservesEdit
import com.junjange.soondong.data.ReservesSportDate
import com.junjange.soondong.data.User
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

        Log.d("tttresponse.body()", response.body().toString())
        Log.d("tttresponse", response.toString())


        return response.isSuccessful

    }

    // Use Retrofit
    suspend fun retrofitReservesCreation(reservesCreation: ReservesCreation): Boolean {
        val response = SoonDongObject.getRetrofitService.postReservesCreation(reservesCreation)

        Log.d("tttresponse.body()", response.body().toString())
        Log.d("tttresponse", response.toString())


        return response.isSuccessful

    }





}