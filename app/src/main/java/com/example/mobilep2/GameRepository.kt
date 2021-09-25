package com.example.mobilep2

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import com.example.mobilep2.database.GameDatabase
import java.util.*

private const val DATABASE_NAME = "game-database"

class GameRepository private constructor(context: Context){

    private val database : GameDatabase = databaseBuilder(
        context.applicationContext,
        GameDatabase::class.java,
        DATABASE_NAME
    ).build()
    private val gameDao = database.gameDao()

    fun getGames(): LiveData<List<Game>> = gameDao.getGames()
    fun getGame(id: UUID): LiveData<Game?> = gameDao.getGame(id)


    companion object {
        private var INSTANCE: GameRepository? = null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = GameRepository(context)
            }
        }
        fun get(): GameRepository {
            return INSTANCE ?:
            throw IllegalStateException("CrimeRepository must be initialized")
        }
    }

}