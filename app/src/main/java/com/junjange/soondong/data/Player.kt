package com.junjange.soondong.data

import com.google.gson.annotations.SerializedName
data class Player(@SerializedName("data") val playerData: ArrayList<PlayerData>)

data class PlayerData(
    @SerializedName("name") val name: String,
    @SerializedName("schoolNum") val schoolNum: String
    )

