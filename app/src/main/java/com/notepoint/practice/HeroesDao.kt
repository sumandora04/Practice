package com.notepoint.practice

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.notepoint.practice.network.Heroes

@Dao
interface HeroesDao {
    @Insert
    fun insertHeroes(heroes: Heroes)


    @Query("Select * from heroes_table")
    fun getAllHeroes():LiveData<List<Heroes>>

    @Query("DELETE from heroes_table")
    fun clearTable()
}