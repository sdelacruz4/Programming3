package com.example.mobilep2.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mobilep2.Game
import com.example.mobilep2.ScoreView

@Database(entities = [Game::class], version=1)
@TypeConverters(GameTypeConverters::class)
abstract class GameDatabase : RoomDatabase() {

    abstract fun gameDao(): GameDao
}