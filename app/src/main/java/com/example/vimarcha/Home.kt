package com.example.vimarcha

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vimarcha.databinding.FragmentAccountBinding
import com.example.vimarcha.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Home : Fragment() {
    // STRING DAN APIKEY
    private lateinit var imdbApi: API
    private lateinit var apiKey: String

    // buat masukin username user
    private lateinit var binding: FragmentAccountBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Mengkoneksikan API
        apiKey = "k_jbn4lx36"
        val retrofit = Retrofit.Builder()
            .baseUrl("https://imdb-api.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        imdbApi = retrofit.create(API::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.progressBar.visibility
        firebaseAuth = FirebaseAuth.getInstance()

        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            val email = currentUser.email
            val username = email?.substring(0, email.indexOf("@"))
            binding.emailtextview.text = username
        }
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = imdbApi.getTrendMovies(apiKey)
                val topRatedMovies = response.items

                val popularResponse = imdbApi.getPopularMovies(apiKey)
                val popularMovies = popularResponse.items
                withContext(Dispatchers.Main) {
                    binding.trendData.apply {
                        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                        adapter = MovieAdapter(topRatedMovies, requireContext())
                    }

                    binding.popularData.apply {
                        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                        adapter = MovieAdapter(popularMovies, requireContext())
                    }
                    binding.progressBar.visibility = View.GONE
                }
            } catch (e: Exception) {
                Log.e("Home", "error", e)
            }
        }
        return binding.root
    }
}