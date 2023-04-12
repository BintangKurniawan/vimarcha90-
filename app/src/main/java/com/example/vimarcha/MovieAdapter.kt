package com.example.vimarcha

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vimarcha.databinding.ListDatamovieBinding
import com.example.vimarcha.modeldata.MovieData

class MovieAdapter(
    private val listMovie: ArrayList<MovieData>,
    private val context: Context
): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding =  ListDatamovieBinding.inflate(LayoutInflater.from(parent.context),
            parent, false
            )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }
    override fun getItemCount(): Int= listMovie.size

    // ADAPTER BUAT TEKS TEKS YG BAKAL DIMASUKIN
    inner class MovieViewHolder(private val binding: ListDatamovieBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movieData: MovieData) {
            with(binding) {
                binding.title.text = movieData.title
                binding.genre.text = movieData.genres
                binding.tahun.text = movieData.year

                Glide.with(itemView).load(movieData.gambar).into(binding.imgPoster)
                cvIdmovie.setOnClickListener{
                    var i = Intent(context, DetailMovieActivity::class.java).apply {
                        putExtra("id", movieData.idMovie)
                    }
                    context.startActivity(i)
                }
            }
        }
    }
}