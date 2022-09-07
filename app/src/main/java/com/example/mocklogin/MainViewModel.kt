package com.example.mocklogin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mocklogin.model.remote.data.loginResponse.LoginResponse
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val repository: Repository) : ViewModel() {
    val loginResponse = MutableStateFlow<LoginResponse?>(null)


    fun login(phone: String, password: String) {
        val map = HashMap<String, String>()
        map["mobileNo"] = phone
        map["password"] = password

        val reqJson: String = Gson().toJson(map)
        val body: RequestBody =
            reqJson.toRequestBody("application/json".toMediaTypeOrNull())
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.login(body)
                if (!response.isSuccessful) {
                    return@launch
                }
                response.body()?.let {
                    loginResponse.emit(it)
                }
            } catch (e: Exception) {
                Log.e("Exception", e.toString())
                e.printStackTrace()
            }
        }
    }
}