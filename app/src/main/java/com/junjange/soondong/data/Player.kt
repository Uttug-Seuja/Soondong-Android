package com.junjange.soondong.data

import com.google.gson.annotations.SerializedName

data class Player(
    val studentId: String,
    val name: String,


)

data class Player1(@SerializedName("data") val playerData: List<PlayerData>)

data class PlayerData(
    @SerializedName("name") val name: String,
    @SerializedName("schoolNum") val schoolNum: String
    )

