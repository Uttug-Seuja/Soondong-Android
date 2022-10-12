package com.junjange.soondong.repository

import android.app.Application


class RegisterRepository (application : Application) {


    // singleton pattern
    companion object {
        private var instance: RegisterRepository? = null

        fun getInstance(application : Application): RegisterRepository? {
            if (instance == null) instance = RegisterRepository(application)
            return instance
        }
    }
//
//    // Use Retrofit
//    suspend fun retrofitNicknameExists(nickName: String): DataBoolean {
//        val response = KuObject.getRetrofitService.postNicknameExists(NickNameBody(nickName))
//
//
//        return response.body() as DataBoolean
//
//    }
//
//    // Use Retrofit
//    suspend fun retrofitCheckFirstTest(): DataBoolean {
//        val response = KuObject.getRetrofitService.getCheckFirstTest()
//
//
//        return if (response.isSuccessful) response.body() as DataBoolean else DataBoolean(false)
//
//    }
//
//    // Use Retrofit
//    suspend fun retrofitSignUp(user: User): DataInt {
//        val response = KuObject.getRetrofitService.postSignUp(user)
//
//        Log.d("tttresponse.body()", response.body().toString())
//        Log.d("tttresponse", response.toString())
//
//
//        return if (response.isSuccessful) response.body() as DataInt else DataInt(0)
//
//    }
//
//    // Use Retrofit
//    suspend fun retrofitSignIn(email: String): DataString {
//        val response = KuObject.getRetrofitService.postSignIn(EmailBody(email))
//
//        Log.d("tttea", email)
//        Log.d("ttt", response.toString())
//        Log.d("ttt", response.body().toString())
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