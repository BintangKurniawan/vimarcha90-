package com.example.vimarcha

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import com.example.vimarcha.modeldata.MovieData
import com.example.vimarcha.modeldata.MovieDataSaved

class SavedActivity : AppCompatActivity() {
    // HAL HAL YANG DIBUTUHKAN
    private lateinit var rv_save: ListView
    private lateinit var savedMovieList: ArrayList<String>
    private lateinit var savedMovieAdapter: ArrayAdapter<String>
    private lateinit var backBtn: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved)

        // tombol kembali
        backBtn = findViewById(R.id.back)
        backBtn.setOnClickListener{
            finish()
        }

        // munculin save
        val sp = getSharedPreferences("user", Context.MODE_PRIVATE)

        rv_save = findViewById(R.id.rv_save)
        savedMovieList = ArrayList()
        var counter: Int = sp.getInt("count", 0)
        var start: Int = 0
        while (start< counter) {
            savedMovieList.add(sp.getString("title"+start, "Unknown").toString())
            start = start+1

        }
        savedMovieAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, savedMovieList.toTypedArray())
        rv_save.adapter = savedMovieAdapter
    }
}