package com.junjange.soondong.repository

import android.app.Application
import android.util.Log

class SplashScreenRepository (application : Application) {


    // singleton pattern
    companion object {
        private var instance: SplashScreenRepository? = null

        fun getInstance(application : Application): SplashScreenRepository? {
            if (instance == null) instance = SplashScreenRepository(application)
            return instance
        }
    }

//    // Use Retrofit
//    suspend fun retrofitCheckFirstTest(): DataBoolean {
//        val response = KuObject.getRetrofitService.getCheckFirstTest()
//
//        return if (response.isSuccessful) response.body() as DataBoolean else DataBoolean(false)
//
//
//    }
//
//    // Use Retrofit
//    suspend fun retrofitSignIn(email: String): DataString {
//        val response = KuObject.getRetrofitService.postSignIn(EmailBody(email))
//
//        Log.d("Ttt", response.body().toString())
//        Log.d("Ttt", response.toString())
//
//        return if (response.isSuccessful) response.body() as DataString else DataString("null")
//
//
//    }
//
//    // Use Retrofit
//    suspend fun retrofitNicknameCurrent(): DataString {
//        val response = KuObject.getRetrofitService.getNicknameCurrent()
//
//        Log.d("ttt", response.body().toString())
//
//
//        return if (response.isSuccessful) response.body() as DataString else DataString("null")
//
//    }


}