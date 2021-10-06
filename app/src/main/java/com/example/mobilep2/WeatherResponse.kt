package com.example.mobilep2

import com.google.gson.annotations.SerializedName

class WeatherResponse {
    @SerializedName("temp")
    lateinit var weatherItems: String
}