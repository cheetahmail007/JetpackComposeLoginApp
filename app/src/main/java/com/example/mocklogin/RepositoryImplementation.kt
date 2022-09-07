package com.example.mocklogin

import com.example.mocklogin.model.remote.ApiServer
import com.example.mocklogin.model.remote.data.loginResponse.LoginResponse
import okhttp3.RequestBody
import retrofit2.Response

class RepositoryImplementation(val apiServer: ApiServer): Repository {
    override suspend fun login(loginReq: RequestBody): Response<LoginResponse> {
        return apiServer.login(loginReq)
    }
}