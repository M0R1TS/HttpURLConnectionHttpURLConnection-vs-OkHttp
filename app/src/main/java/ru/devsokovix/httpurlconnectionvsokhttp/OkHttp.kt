package ru.devsokovix.httpurlconnectionvsokhttp

import android.os.Bundle
import android.telecom.Call
import androidx.appcompat.app.AppCompatActivity
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class OkHttp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://reqres.in/api/users/2")
            .build()

        client.newCall(request).enqueue(object : Callback {
            //Переопределяем метод, что будет, если мы не сможем получить ответ на запрос
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                e.printStackTrace()
            }
            //Переопределяем метод, что будет, если мы сможем получить ответ на запрос
            override fun onResponse(call: okhttp3.Call, response: Response) {
                //Здесь тоже надо обернуть в try-catch
                try {
                    val responseBody = response.body()
                    println("!!! ${responseBody?.string()}")
                } catch (e: Exception) {
                    println(response)
                    e.printStackTrace()
                }
            }
        })
    }
}