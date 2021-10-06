package com.example.mobilep2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


class OpenWeatherFetchr {
    private val TAG = "OpenWeatherFetchr"

    private val OWeatherApi: OpenWeatherApi
    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        OWeatherApi = retrofit.create(OpenWeatherApi::class.java)
    }

    fun fetchWeather(): LiveData<String> {
        val responseLiveData: MutableLiveData<String> = MutableLiveData()
        val owRequest: Call<OpenResponse> = OWeatherApi.fetchWeather()
        owRequest.enqueue(object : Callback<OpenResponse> {
            override fun onFailure(call: Call<OpenResponse>, t: Throwable) {
                Log.e(TAG, "Failed to fetch photos", t)
            }
            override fun onResponse(
                call: Call<OpenResponse>,
                response: Response<OpenResponse>
            ) {
                Log.d(TAG, "Response received")
                val owResponse: OpenResponse? = response.body()
                val wResponse: WeatherResponse? = owResponse?.weathers
                var weatherItems: String? = wResponse?.weatherItems
                responseLiveData.value = weatherItems

            }
        })
        return responseLiveData
    }

}

