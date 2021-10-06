package com.example.mobilep2

import com.google.gson.annotations.SerializedName

class OpenResponse {
    @SerializedName("current")
    lateinit var weathers: WeatherResponse
}

