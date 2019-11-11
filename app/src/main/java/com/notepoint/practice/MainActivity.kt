package com.notepoint.practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.notepoint.practice.network.Heroes
import com.notepoint.practice.network.RetrofitInstance
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val application = requireNotNull(this).application
        val dataSource = HeroesDatabase.getDbInstance(this).heroesDao
        val factory = ViewModelFactory(dataSource, application)
        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)

        viewModel.heroesFromDb().observe(this, Observer {
            it?.let {
                Log.d("From Db", "" + it)
                heroes_recycler.layoutManager = LinearLayoutManager(this)
                heroes_recycler.adapter = HeroesAdapter(it, HeroesAdapter.HeroesClickListener {
                    Toast.makeText(this, it.heroName, Toast.LENGTH_SHORT).show()
                })
            }
        })


    }

}
