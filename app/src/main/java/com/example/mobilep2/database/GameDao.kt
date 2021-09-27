package com.example.mobilep2.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.mobilep2.Game
import java.util.*

@Dao
interface GameDao {
    @Query("SELECT * FROM table_game")
    fun getGames(): LiveData<List<Game>>
    @Query("SELECT * FROM table_game WHERE id=(:id)")
    fun getGame(id: UUID): LiveData<Game?>
    abstract fun updateGame(game: Game)


}