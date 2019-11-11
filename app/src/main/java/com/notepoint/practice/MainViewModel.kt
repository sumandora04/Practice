package com.notepoint.practice

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.notepoint.practice.network.Heroes
import com.notepoint.practice.network.RetrofitInstance
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(val dataSource: HeroesDao, application: Application) : AndroidViewModel(application) {

    val job = Job()
    val scope = CoroutineScope(job + Dispatchers.Main)

    val allHeroes = dataSource.getAllHeroes()

    init {
        getHeroesList()
    }


    fun heroesFromDb(): LiveData<List<Heroes>> {
        return allHeroes
    }

    fun insertData(heroes: Heroes) {
        scope.launch {
            withContext(Dispatchers.IO) {
                dataSource.insertHeroes(heroes)
            }
        }
    }

    fun clearHeroesTable() {
        scope.launch {
            withContext(Dispatchers.IO) {
                dataSource.clearTable()
            }
        }
    }

    fun getHeroesList() {
        RetrofitInstance.retrofitInstance.getHeroes().enqueue(object : Callback<List<Heroes>> {

            override fun onResponse(call: Call<List<Heroes>>, response: Response<List<Heroes>>) {
                if (response.isSuccessful) {
                    Log.d("Response: ", "" + response.body())
                    val heroesList = response.body()
                    heroesList?.let {

                        clearHeroesTable()

                        for (hero in it) {
                            insertData(hero)
                        }
                    }

                }

            }

            override fun onFailure(call: Call<List<Heroes>>, t: Throwable) {
                Log.d("Response: ", "" + t.localizedMessage)
            }

        })


    }
}

