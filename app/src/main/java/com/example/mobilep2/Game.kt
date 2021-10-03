package com.example.mobilep2

import android.widget.TextView
import androidx.lifecycle.ViewModel
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "table_game")
data class Game(@PrimaryKey var id: UUID = UUID.randomUUID(), @ColumnInfo(name = "teamAScore") var  teamAScore: Int = 0,
           @ColumnInfo(name = "teamBScore") var  teamBScore: Int = 0, @ColumnInfo(name = "teamAName")var  teamAName: String = "Team A",
           @ColumnInfo(name = "teamBName")var  teamBName: String = "Team B", var  gameTitle: String = "",
           @ColumnInfo(name = "date")var  date: Date = Date()){
}