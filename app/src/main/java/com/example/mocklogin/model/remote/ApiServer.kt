package com.example.mocklogin.model.remote

import com.example.mocklogin.model.remote.data.loginResponse.LoginResponse
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ApiServer {
    @Headers("Content-type: application/json")
    @POST("appUser/login")
    suspend fun login(@Body loginReq: RequestBody): Response<LoginResponse>
}