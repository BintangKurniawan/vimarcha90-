package com.example.vimarcha

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.vimarcha.databinding.ActivityDetailMovieBinding
import com.example.vimarcha.modeldata.MovieData
import com.example.vimarcha.modeldata.MovieDetailData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMovieActivity : AppCompatActivity() {
    // HAL HAL YANG DIBUTUHKAN
    private lateinit var binding : ActivityDetailMovieBinding
    private var saved: Boolean = false
    private var savedMovie: ArrayList<MovieData> =
        ArrayList<MovieData>()
    private var title = String
    private var gambar = String
    private var idMovie = String
    var typeView: Button? = null
    private val sp: SharedPreferences? = null
    var save: ImageButton? = null
    var b : Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        b = intent.extras

        // UNTUK FITUR SAVE
        val sp = getSharedPreferences("user", Context.MODE_PRIVATE)
        val editor = sp.edit()

        // UNTUK FITUR WATCH
        val sp2 = getSharedPreferences("watch", Context.MODE_PRIVATE)
        val editor2 = sp2.edit()

        // BUAT API
        val id = b?.getString("id")
        val apiKey = "k_jbn4lx36"

        // BALIK KE SEBELUMNYA
        val back = findViewById<ImageView>(R.id.back)
        back.setOnClickListener(View.OnClickListener {
            finish()
        })

        // MASUKKIN DATA DARI API
        RClient.instances.getDetailMovie(apiKey, id).enqueue(object: Callback<MovieDetailData> {
            override fun onResponse(
                call: Call<MovieDetailData>,
                response: Response<MovieDetailData>
            ) {
                binding.title.text =response.body()?.title
                binding.typeMovie.text = response.body()?.type
                binding.genre.text = response.body()?.genres
                binding.directorMv.text = response.body()?.directors
                binding.contentRatings.text = response.body()?.contentRating
                binding.plot.text = response.body()?.plot
                binding.typeMovie2.text = response.body()?.type
                binding.ratings.text = response.body()?.imDbRating
                Glide.with(this@DetailMovieActivity).load(response.body()?.gambar).into(binding.imgPoster)
            }

            override fun onFailure(call: Call<MovieDetailData>, t: Throwable) {
            }

        })

        // FITUR SAVE

        var isSaved = false
        var counter: Int = sp.getInt("count", 0)
        binding.saved.setColorFilter(ContextCompat.getColor(this, R.color.abu))
        binding.saved.setOnClickListener{
            val titles = binding.title.text.toString()
            for (i in 0 until counter) {
                val savedTitle = sp.getString("title"+i, "") ?: ""
                if (savedTitle == titles) {
                    isSaved = true
                    break
                }
            }

            // MENGECEK AGAR TAK ADA DUPLIKAT DATA
            if(!isSaved) {
                editor.putString("title"+counter, titles)
                editor.putInt("count", counter+1)
                editor.apply()
                binding.saved.setColorFilter(ContextCompat.getColor(this, R.color.biru))

                Toast.makeText(
                    applicationContext,
                    "Film ditambahkan ke daftar simpan",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                binding.saved.setColorFilter(ContextCompat.getColor(this, R.color.biru))
                Toast.makeText(
                    applicationContext,
                    "Film sudah ada di daftar simpan",
                    Toast.LENGTH_SHORT
                ).show()
            }

            isSaved = false
        }

        // FITUR WATCHED
        var isWatched = false
        binding.watched.setColorFilter(ContextCompat.getColor(this, R.color.abu))
        binding.watched.setOnClickListener {
            val titles = binding.title.text.toString()
            for (i in 0 until counter) {
                val savedTitle = sp2.getString("title"+i, "") ?: ""
                if (savedTitle == titles) {
                    isWatched = true
                    break
                }
            }

            // MENGECEK AGAR TAK ADA DUPLIKAT DATA
            if(!isSaved) {
                editor2.putString("title"+counter, titles)
                editor2.putInt("count", counter+1)
                editor2.apply()
                binding.watched.setColorFilter(ContextCompat.getColor(this, R.color.beureum))

                Toast.makeText(
                    applicationContext,
                    "Film ditambahkan ke daftar sudah ditonton",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                binding.watched.setColorFilter(ContextCompat.getColor(this, R.color.beureum))
                Toast.makeText(
                    applicationContext,
                    "Film sudah ada di daftar ditonton",
                    Toast.LENGTH_SHORT
                ).show()
            }

            isWatched = false
        }
    }
}
