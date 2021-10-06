package com.example.mobilep2

import android.widget.TextView
import androidx.lifecycle.ViewModel
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import java.util.*


class ScoreView(): ViewModel() {
    private var  teamAScore: Int = 0;
    private var  teamBScore: Int = 0;
    private var  teamAName: String = "Team A";
    private var  teamBName: String = "Team B";
    private var  gameTitle: String = "";
    private var  gameDate: String = "";


    fun getTeamAScore(): Int{
        return teamAScore;
    }

    fun updateTeamAScore(score: Int){
        teamAScore += score
    }


    fun getTeamBScore(): Int{
        return teamBScore;
    }

    fun updateTeamBScore(score: Int){
        teamBScore += score
    }


    fun getTeamAName():String{
        return teamAName
    }


    fun getTeamBName():String{
        return teamBName
    }

    fun updateTeamAName(name: String){
        teamAName = "Team $name"
    }

    fun updateTeamBName(name: String){
        teamBName = "Team $name"

    }


    fun getGameTitle(): String{
        return gameTitle
    }

    fun updateGameTitle(title: String){
        gameTitle = title
    }


    fun getGameDate(): String{
        return gameDate
    }

    fun updateGameDate(date: String){
        gameDate = date
    }

    fun resetScores(){
        teamAScore = 0
        teamBScore = 0
    }

}