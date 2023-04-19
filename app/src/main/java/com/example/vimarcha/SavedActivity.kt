package com.example.vimarcha

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vimarcha.modeldata.MovieData
import com.example.vimarcha.modeldata.MovieDataSaved

class SavedActivity : AppCompatActivity() {
    // HAL HAL YANG DIBUTUHKAN
//    private lateinit var rv_save: ListView
//    private lateinit var savedMovieList: ArrayList<String>
//    private lateinit var savedMovieAdapter: ArrayAdapter<String>
//    private lateinit var backBtn: ImageView

    private lateinit var backBtn: ImageView
    private lateinit var rv_save: RecyclerView
    private lateinit var savedMovieAdapter: SavedMovieAdapter
    private lateinit var savedMovieList: MutableList<String>
    private lateinit var sp: SharedPreferences
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_saved)
//
//        // tombol kembali
//        backBtn = findViewById(R.id.back)
//        backBtn.setOnClickListener{
//            finish()
//        }
//
//        // munculin save
//        val sp = getSharedPreferences("user", Context.MODE_PRIVATE)
//
//        rv_save = findViewById(R.id.rv_save)
//        savedMovieList = mutableListOf()
//        var counter: Int = sp.getInt("count", 0)
//        var start: Int = 0
//        while (start< counter) {
//            savedMovieList.add(sp.getString("title"+start, "Unknown").toString())
//            start = start+1
//
//        }
//        savedMovieAdapter = SavedMovieAdapter(savedMovieList)
//        rv_save.adapter = savedMovieAdapter
//    }
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_saved)

    // tombol kembali
    backBtn = findViewById(R.id.back)
    backBtn.setOnClickListener {
        finish()
    }

    // munculin save
    val sp = getSharedPreferences("user", Context.MODE_PRIVATE)

    rv_save = findViewById(R.id.rv_save)
    savedMovieList = mutableListOf()
    var counter: Int = sp.getInt("count", 0)
    var start: Int = 0
    while (start < counter) {
        savedMovieList.add(sp.getString("title"+start, "Unknown").toString())
        start = start + 1
    }
    savedMovieAdapter = SavedMovieAdapter(savedMovieList)
    rv_save.layoutManager = LinearLayoutManager(this)
    rv_save.adapter = savedMovieAdapter
    rv_save.addOnItemTouchListener(
        RecyclerItemClickListener(this, rv_save, object : RecyclerItemClickListener.OnItemClickListener {
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

//override fun onCreate(savedInstanceState: Bundle?) {
//    super.onCreate(savedInstanceState)
//    setContentView(R.layout.activity_saved)
//
//    // tombol kembali
//    backBtn = findViewById(R.id.back)
//    backBtn.setOnClickListener{
//        finish()
//    }
//
//    // munculin save
//    val sp = getSharedPreferences("user", Context.MODE_PRIVATE)
//
//    rv_save = findViewById(R.id.rv_save)
//    savedMovieList = ArrayList()
//    var counter: Int = sp.getInt("count", 0)
//    var start: Int = 0
//    while (start< counter) {
//        savedMovieList.add(sp.getString("title"+start, "Unknown").toString())
//        start = start+1
//
//    }
//    savedMovieAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, savedMovieList.toTypedArray())
//    rv_save.adapter = savedMovieAdapter
//}
}
