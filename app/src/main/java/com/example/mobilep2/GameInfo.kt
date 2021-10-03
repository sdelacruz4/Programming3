package com.example.mobilep2

import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class GameInfo: ViewModel() {
    val games = mutableListOf<ScoreView>()

    private fun randomName(): String = List(8) {
        (('a'..'z') + ('A'..'Z') + ('0'..'9')).random()
    }.joinToString("")

    private fun randomNumber(): Int {
        return (0..100).random()
    }
    private fun createDate(): String {
        val calendar: Calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("E LLL dd KK:mm:ss z yyyy")
        return simpleDateFormat.format(calendar.time).toString()
    }

    init{
        for (i in 0 until 100){

            val game = ScoreView()
            game.updateGameDate(createDate())
            game.updateGameTitle("Game #$i")
            game.updateTeamAName(randomName())
            game.updateTeamBName(randomName())
            game.updateTeamAScore(randomNumber())
            game.updateTeamBScore(randomNumber())
            games += game
        }
    }

}