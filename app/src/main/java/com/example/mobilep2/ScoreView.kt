package com.example.mobilep2

import android.widget.TextView
import androidx.lifecycle.ViewModel
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ScoreView: ViewModel() {
    private var  teamAScore: Int = 0;
    private var  teamBScore: Int = 0;

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

    fun resetScores(){
        teamAScore = 0
        teamBScore = 0
    }
}