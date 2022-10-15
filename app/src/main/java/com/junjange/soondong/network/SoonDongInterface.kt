package com.junjange.soondong.network


import com.google.gson.JsonObject
import com.junjange.soondong.data.*
import com.junjange.soondong.utils.API
import retrofit2.Response
import retrofit2.http.*
import retrofit2.http.Body
import retrofit2.http.POST

interface SoonDongInterface {

    // 회원가입
    @Headers("Content-Type: application/json")
    @POST(API.POST_USERS_CREATION)
    suspend fun postUsersCreation(
        @Body user: User,
    ): Response<JsonObject>


    // 로그인
    @Headers("Content-Type: application/json")
    @POST(API.POST_USERS_LOGIN)
    suspend fun postUsersLogin(
        @Body login: Login,
    ): Response<Player1>


    // 참가 신청
    @Headers("Content-Type: application/json")
    @POST(API.POST_PARTICIPATION)
    suspend fun postParticipation(
        @Body participation: Participation,
    ): Response<JsonObject>

    // 참가 취소
    @Headers("Content-Type: application/json")
    @DELETE(API.DELETE_PARTICIPATION)
    suspend fun deleteParticipation(
        @Body participation: Participation,
    ): Response<JsonObject>

    // 매칭에 참가한 유저 확인
    @GET(API.GET_PARTICIPANT_USER_INFO)
    suspend fun getParticipantUserInfo(
        @Path("reserveId") reserveId : Int,

        ): Response<Player1>

    // 경기 생성
    @Headers("Content-Type: application/json")
    @POST(API.POST_RESERVES_CREATION)
    suspend fun postReservesCreation(
        @Body reservesCreation: ReservesCreation,
    ): Response<JsonObject>

    // 경기 수정
    @Headers("Content-Type: application/json")
    @POST(API.POST_RESERVES_EDIT)
    suspend fun postReservesEdit(
        @Body reservesEdit: ReservesEdit,
    ): Response<JsonObject>


    // 경기 삭제
    @Headers("Content-Type: application/json")
    @DELETE(API.DELETE_RESERVES)
    suspend fun deleteReserves(
        @Path("reserveId") reserveId : Int,

    ): Response<JsonObject>

    // 스포츠와 날짜 별로 경기 조회
    @Headers("Content-Type: application/json")
    @GET(API.GET_RESERVES_SPORT_DATE)
    suspend fun getReservesSportDate(
        @Path("sport") sport : String,
        @Path("day") day : String,


        ): Response<ReservesSportDate>


    // 오늘 날짜로 경기 조회
    @Headers("Content-Type: application/json")
    @GET(API.GET_RESERVES_SPORT_TODAY)
    suspend fun getReservesSportToday(
        @Path("today") today : String,


        ): Response<ReservesSportDate>


    // 경기 세부 정보 제공
    @Headers("Content-Type: application/json")
    @GET(API.GET_RESERVES_Info)
    suspend fun getReservesInfo(
        @Path("reserveId") reserveId : Int,


        ): Response<ReservesInfo>


}