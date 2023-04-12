package com.example.vimarcha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class Navbar : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navbar)

        // ID BOTTOM NAV
        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        // KOMPONEN NAV
        val homeFragment = Home()
        val searchFragment = search()
        val accountFragment = account()

        // MAIN ACTIVITY NA
        makeCurrentFragment(homeFragment)

        // LAMUN DIPENCET
        bottomNavigationView.setOnNavigationItemSelectedListener { item: MenuItem ->
            var fragment: Fragment? = null
            when (item.itemId) {
                R.id.home -> fragment = homeFragment
                R.id.search -> fragment = searchFragment
                R.id.account -> fragment = accountFragment
            }
            if (fragment != null) {
                makeCurrentFragment(fragment)
            }
            true
        }
    }

    // MUN GONTA GANTI CEGANA
    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout2, fragment)
            commit()
        }
    }
}
