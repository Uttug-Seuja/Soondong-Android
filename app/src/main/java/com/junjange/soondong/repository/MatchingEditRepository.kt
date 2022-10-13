package com.junjange.soondong.repository

import android.app.Application
import android.util.Log
import com.google.gson.JsonObject
import com.junjange.soondong.data.ReservesCreation
import com.junjange.soondong.data.ReservesEdit
import com.junjange.soondong.data.ReservesSportDate
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
    suspend fun retrofitReservesEdit(reservesEdit: ReservesEdit): JsonObject {
        val response = SoonDongObject.getRetrofitService.postReservesEdit(reservesEdit)

        Log.d("tttresponse.body()", response.body().toString())
        Log.d("tttresponse", response.toString())


        return if (response.isSuccessful) response.body() as JsonObject else JsonObject()

    }

    // Use Retrofit
    suspend fun retrofitReservesCreation(reservesCreation: ReservesCreation): JsonObject {
        val response = SoonDongObject.getRetrofitService.postReservesCreation(reservesCreation)

        Log.d("tttresponse.body()", response.body().toString())
        Log.d("tttresponse", response.toString())


        return if (response.isSuccessful) response.body() as JsonObject else JsonObject()

    }



}