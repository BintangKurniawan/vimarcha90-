package com.example.vimarcha

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class WatchedActivity : AppCompatActivity() {
    //HAL HAL YANG DIBUTUHKAN
    private lateinit var rv_watch: RecyclerView
    private lateinit var savedMovieList: MutableList<String>
    private lateinit var savedMovieAdapter: SavedMovieAdapter
    private lateinit var backBtn: ImageView
//    @SuppressLint("MissingInflatedId")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_watched)
//
//        // tombol kembali
//        backBtn = findViewById(R.id.back)
//        backBtn.setOnClickListener{
//            finish()
//        }
//
//        // munculin save
//        val sp = getSharedPreferences("watch", Context.MODE_PRIVATE)
//        rv_watch = findViewById(R.id.rv_watch)
//        savedMovieList = ArrayList()
//        var counter: Int = sp.getInt("count", 0)
//        var start: Int = 0
//        while (start< counter) {
//            savedMovieList.add(sp.getString("title"+start, "Unknown").toString())
//            start = start+1
//
//        }
//        savedMovieAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, savedMovieList.toTypedArray())
//        rv_watch.adapter = savedMovieAdapter
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watched)
    // tombol kembali
        backBtn = findViewById(R.id.back)
        backBtn.setOnClickListener{
            finish()
        }

        // munculin save
        val sp = getSharedPreferences("watch", Context.MODE_PRIVATE)
        rv_watch = findViewById(R.id.rv_watch)
        savedMovieList = mutableListOf()
        var counter: Int = sp.getInt("count", 0)
        var start: Int = 0
        while (start< counter) {
            savedMovieList.add(sp.getString("title"+start, "Unknown").toString())
            start = start+1

        }
        savedMovieAdapter = SavedMovieAdapter(savedMovieList)
        rv_watch.layoutManager = LinearLayoutManager(this)
        rv_watch.adapter = savedMovieAdapter
        rv_watch.addOnItemTouchListener(
            RecyclerItemClickListener(this, rv_watch, object : RecyclerItemClickListener.OnItemClickListener {
                override fun onItemClick(view: View, position: Int) {
                    // remove the item from savedMovieList and update the RecyclerView
                    savedMovieList.removeAt(position)
                    savedMovieAdapter.notifyItemRemoved(position)

                    // update the SharedPreferences
                    val editor = sp.edit()
                    editor.remove("title$position")
                    editor.putInt("count", savedMovieList.size)
                    for (i in 0 until savedMovieList.size) {
                        editor.putString("title$i", savedMovieList[i])
                    }
                    editor.apply()
                }

                override fun onLongItemClick(view: View?, position: Int) {
                    // do nothing
                }
            })
        )
    }


}