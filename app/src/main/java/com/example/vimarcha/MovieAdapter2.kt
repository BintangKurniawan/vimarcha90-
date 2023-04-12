package com.example.vimarcha

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vimarcha.databinding.ListSearchdataBinding
import com.example.vimarcha.modeldata.MovieData

class MovieAdapter2(
    private val listMovie: ArrayList<MovieData>,
    private val context: Context
): RecyclerView.Adapter<MovieAdapter2.MovieViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieAdapter2.MovieViewHolder {
        val binding = ListSearchdataBinding.inflate(LayoutInflater.from(parent.context),
                parent, false
            )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieAdapter2.MovieViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }

    override fun getItemCount(): Int= listMovie.size

    inner class MovieViewHolder(private val binding: ListSearchdataBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movieData: MovieData) {
            with(binding) {
                binding.tvTitle.text = movieData.title
                binding.tvRate.text = movieData.description
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