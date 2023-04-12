package com.example.vimarcha

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vimarcha.databinding.FragmentSearchBinding
import com.example.vimarcha.modeldata.MovieData
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class search : Fragment() {
    // STRING DAN APIKEY
    private lateinit var imdbApi: API
    private lateinit var apiKey: String

    // buat masukin username user
    private lateinit var binding: FragmentSearchBinding
    private lateinit var firebaseAuth: FirebaseAuth

    private val movies: List<MovieData> = listOf()
    private lateinit var movieAdapter: MovieAdapter

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
        // Inflate the layout for this fragment
        val binding = FragmentSearchBinding.inflate(inflater, container, false)

        // MASUKIN USERNAME
        firebaseAuth = FirebaseAuth.getInstance()

        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            val email = currentUser.email
            val username = email?.substring(0, email.indexOf("@"))
            binding.emailtextview.text = username
        }

        binding.search.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val  title = binding.search.text.toString()
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val response = imdbApi.getSearch(apiKey, title)
                        val searchResp = response.execute().body()?.results
                        withContext(Dispatchers.Main) {
                            binding.rvSearch.apply {
                                layoutManager =
                                    LinearLayoutManager(context)
                                adapter =
                                    MovieAdapter2(searchResp as ArrayList<MovieData>, requireContext())
                            }
                        }
                    } catch (e: Exception) {
                        Log.e("Search", "error", e)
                    }
                }
                true
            } else {
                false
            }
        }
        binding.btnSearch.setOnClickListener {
            val  title = binding.search.text.toString()
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response = imdbApi.getSearch(apiKey, title)
                    val searchResp = response.execute().body()?.results
                    withContext(Dispatchers.Main) {
                        binding.rvSearch.apply {
                            layoutManager =
                                LinearLayoutManager(context)
                            adapter =
                                MovieAdapter2(searchResp as ArrayList<MovieData>, requireContext())
                        }
                    }
                } catch (e: Exception) {
                    Log.e("Search", "error", e)
                }
            }
        }

        return binding.root
    }
}
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        binding.rvSearch.setHasFixedSize(true)
//        binding.rvSearch.layoutManager = LinearLayoutManager(context)
//
//        val expression =binding.search.text.toString()
//        movieAdapter = MovieAdapter(movies as ArrayList<MovieData>)
//        binding.rvSearch.apply {
//            adapter = movieAdapter
//            layoutManager = LinearLayoutManager(requireContext())
//        }
//
////        binding.btnSearch.setOnClickListener {
////            val q = binding.search.text.toString().trim()
////            if (q.isNotEmpty()) {
////                searchMovies(q)
////            }
////        }
//    }
//
////    private fun searchMovies(q: String) {
////        lifecycleScope.launch{
////            try {
////                val resp = imdbApi.getSearch(apiKey, q)
////                val respSrch = resp.results
////                withContext(Dispatchers.Main) {
////                    binding.rvSearch.apply {
////                        layoutManager = LinearLayoutManager(context, LinearLayoutManager, false)
////                        adapter = Mov
////                    }
////                }
////            }
////        }
////    }
//
//}