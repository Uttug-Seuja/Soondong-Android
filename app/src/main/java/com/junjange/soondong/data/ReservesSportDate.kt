package com.junjange.soondong.data

import com.google.gson.annotations.SerializedName

data class ReservesSportDate(@SerializedName("data") val reservesSportDateData: List<ReservesSportDateData>)

data class ReservesSportDateData(

    @SerializedName("sport") val sport: String,
    @SerializedName("reserveDate") val reserveDate: String,
    @SerializedName("startT") val startT: String,
    @SerializedName("endT") val endT: String,
    @SerializedName("title") val title: String,
    @SerializedName("explanation") val explanation: String,
    @SerializedName("currentNum") val currentNum: Int,
    @SerializedName("recruitmentNum") val recruitmentNum: Int,


    )


