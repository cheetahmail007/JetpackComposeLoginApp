package com.example.mocklogin

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContent {
            PreviewMethod()
        }
    }

    @Composable
    fun PreviewMethod() {
        Column {
            var email by remember { mutableStateOf("") }
            Row(modifier = Modifier.height(60.dp)) {
                Text(
                    text = "Email: ",
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    style = MaterialTheme.typography.h6
                )
                Spacer(modifier = Modifier.width(20.dp))
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier
                        .width(180.dp)
                        .height(60.dp)
                )
                Spacer(modifier = Modifier.width(20.dp))

            }
            var phone by remember { mutableStateOf("") }
            Row(modifier = Modifier.height(60.dp)) {
                Text(
                    text = "Phone: ",
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    style = MaterialTheme.typography.h6
                )
                Spacer(modifier = Modifier.width(20.dp))
                TextField(
                    value = phone,
                    onValueChange = { phone = it },
                    modifier = Modifier
                        .width(180.dp)
                        .height(60.dp)
                )
                Spacer(modifier = Modifier.width(20.dp))

            }
            var password by remember { mutableStateOf("") }
            Row(modifier = Modifier.height(60.dp)) {
                Text(
                    text = "Password: ",
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    style = MaterialTheme.typography.h6
                )
                Spacer(modifier = Modifier.width(20.dp))
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier
                        .width(180.dp)
                        .height(60.dp)
                )
                Spacer(modifier = Modifier.width(20.dp))

            }

            Button(
                onClick = { login(email, phone, password) },
                modifier = Modifier
                    .width(120.dp)
                    .height(60.dp)
            ) {
                Text(text = "Login")
            }

            mainViewModel.loginResponse.collectAsState().let {
                it.value?.let {
                    Log.e("value", it.toString())
                    ShowMessage(it.message)
                }

            }
        }
    }@Composable
    fun ShowMessage(message: String) {
        Column {
            Text(text = message)
        }
    }

    fun login(email: String, phone: String, password: String){
        var error = false
        var message = ""
        if(!Regex("^[a-z0-9]{6,}@[a-z]+(.[a-z]+)+$").matches(email)){
            error = true
            message = "Email Error"
        }
        if(!Regex("^[0-9]{8,13}$").matches(phone)){
            error = true
            message = "Phone Error"
        }
        if(!Regex("^[a-z0-9]{8,}$").matches(password)){
            error = true
            message = "Psssword Error"
        }

        if(error){
            val builder = AlertDialog.Builder(this)
                .setTitle("Login Error")
                .setMessage(message)
                .setPositiveButton("Ok") { _, _ ->
                }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(true)
            alertDialog.show()
        } else {
            mainViewModel.login(phone, password)
        }



    }


}



