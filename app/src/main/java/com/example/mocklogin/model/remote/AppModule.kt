package com.example.mocklogin.model.remote

import android.util.Log
import com.example.mocklogin.Repository
import com.example.mocklogin.RepositoryImplementation
import com.example.mocklogin.model.remote.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi(): ApiServer {
        val retrofit = Retrofit.Builder()
            .client(OkHttpClient().newBuilder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        return retrofit.create(ApiServer::class.java)
    }
    @Provides
    @Singleton
    fun provideRepository(apiServer: ApiServer): Repository {
        return RepositoryImplementation(apiServer)
    }
}