package com.junjange.soondong.repository

import android.app.Application
import android.util.Log
import com.google.gson.JsonObject
import com.junjange.soondong.data.User
import com.junjange.soondong.network.SoonDongObject


class RegisterRepository (application : Application) {

    // singleton pattern
    companion object {
        private var instance: RegisterRepository? = null

        fun getInstance(application : Application): RegisterRepository? {
            if (instance == null) instance = RegisterRepository(application)
            return instance
        }
    }

    // Use Retrofit
    suspend fun retrofitSignUp(user: User): Boolean {
        val response = SoonDongObject.getRetrofitService.postUsersCreation(user)
        return response.isSuccessful
    }
}