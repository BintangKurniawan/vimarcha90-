package com.example.vimarcha

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.vimarcha.databinding.FragmentAccountBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

private lateinit var binding: FragmentAccountBinding
private lateinit var database: DatabaseReference
class account : Fragment() {

    private lateinit var binding: FragmentAccountBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // BUAT AMBIL USERNAME
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        firebaseAuth = FirebaseAuth.getInstance()
        // BUAT AMBIL USERNAME
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            val email = currentUser.email
            val username = email?.substring(0, email.indexOf("@"))
            binding.emailtextview.text = username
        }

        // MASUK KE SAVE
        val saved = binding.disimpan
        saved.setOnClickListener(View.OnClickListener {
            val i = Intent(context, SavedActivity::class.java)
            startActivity(i)
        })

        // MASUK KE WATCH
        val watched = binding.watched
        watched.setOnClickListener {
            val i = Intent(context, WatchedActivity::class.java)
            startActivity(i)
        }

        return binding.root
    }
}