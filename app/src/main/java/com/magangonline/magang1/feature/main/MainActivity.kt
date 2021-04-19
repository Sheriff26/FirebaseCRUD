package com.magangonline.magang1.feature.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.magangonline.magang1.HistoryFragment
import com.magangonline.magang1.feature.home.HomeFragment
import com.magangonline.magang1.feature.profile.ProfileFragment
import com.magangonline.magang1.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener,
    BottomNavigationView.OnNavigationItemReselectedListener {

    lateinit var homeFragment: HomeFragment
    lateinit var historyFragment: HistoryFragment
    lateinit var profileFragment: ProfileFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        homeFragment = HomeFragment()
        addFragment(homeFragment)

        bottom_nav.setOnNavigationItemSelectedListener(this)
        bottom_nav.setOnNavigationItemReselectedListener(this)
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
            .replace(R.id.content, fragment)
            .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                homeFragment =
                    HomeFragment()
                addFragment(homeFragment)
            }
            R.id.history -> {
                historyFragment =
                    HistoryFragment()
                addFragment(historyFragment)
            }
            R.id.profile -> {
                profileFragment =
                    ProfileFragment()
                addFragment(profileFragment)
            }
        }
        return true
    }

    override fun onNavigationItemReselected(item: MenuItem) {
        when (item.itemId){
            R.id.home -> {}
            R.id.history -> {}
            R.id.profile -> {}
        }
    }

}