package com.example.mobilep2

import retrofit2.Call
import retrofit2.http.GET

interface OpenWeatherApi {
    @GET( "data/2.5/onecall?lat=42.26"+"&lon=-71.8"+"&exclude=hourly,minutely"+"&appid=a78925143f508840462577e653873082")
    fun fetchWeather(): Call<OpenResponse>
}