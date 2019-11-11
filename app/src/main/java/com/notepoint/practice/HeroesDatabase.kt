package com.notepoint.practice

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.notepoint.practice.network.Heroes

@Database(entities = [Heroes::class],version = 1,exportSchema = false)
abstract class HeroesDatabase:RoomDatabase() {
    abstract val heroesDao:HeroesDao
    companion object{
        @Volatile
        private var INSTANCE:HeroesDatabase? = null

        fun getDbInstance(context: Context):HeroesDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        HeroesDatabase::class.java,
                        "heroes_database"
                    ).fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}