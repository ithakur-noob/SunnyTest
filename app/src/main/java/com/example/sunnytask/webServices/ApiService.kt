package com.example.sunnytask.webServices

import com.example.sunnytask.MsgList
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface ApiService {
    @POST("auth/loginApp")
    suspend fun login(
        @Body map: HashMap<String, String>
    ): Response<String>

    @GET("whatsapp/getMessageHistoryActiveStatus/{phone}/{page}/{entries}")
    suspend fun getMessageHistory(
        @Path("phone") phone: String,
        @Path("page") page: Int,
        @Path("entries") entries: Int
    ): Response<MsgList>
}