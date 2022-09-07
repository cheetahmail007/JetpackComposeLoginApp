package com.example.mocklogin

import com.example.mocklogin.model.remote.data.loginResponse.LoginResponse
import okhttp3.RequestBody
import retrofit2.Response

interface Repository {
    suspend fun login(loginReq: RequestBody): Response<LoginResponse>
}