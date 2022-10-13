package com.junjange.soondong.repository

import android.app.Application
import com.google.gson.JsonObject
import com.junjange.soondong.data.Login
import com.junjange.soondong.data.Player1
import com.junjange.soondong.network.SoonDongObject

class SigninRepository (application : Application) {


    // singleton pattern
    companion object {
        private var instance: SigninRepository? = null

        fun getInstance(application : Application): SigninRepository? {
            if (instance == null) instance = SigninRepository(application)
            return instance
        }
    }


    // Use Retrofit
    suspend fun retrofitSignIn(login: Login): Player1 {
        val response = SoonDongObject.getRetrofitService.postUsersLogin(login)


        return response.body() as Player1


    }






}