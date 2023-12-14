package com.fabiansuarez.micartera.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.fabiansuarez.micartera.R
import com.fabiansuarez.micartera.databinding.ActivityMainBinding
import com.fabiansuarez.micartera.view.fragment.DetailAccountFragment
import com.fabiansuarez.micartera.view.fragment.HomeFragment

class MainActivity : AppCompatActivity() {

    lateinit var fragmentManager: FragmentManager
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_menu_1 -> openFragment(HomeFragment())
                R.id.nav_menu_2 -> openFragment(DetailAccountFragment())
                R.id.nav_menu_3 -> Toast.makeText(this, "MENU 3", Toast.LENGTH_SHORT).show()
                R.id.nav_menu_4 -> Toast.makeText(this, "MENU 4", Toast.LENGTH_SHORT).show()
                R.id.nav_menu_5 -> startActivity(Intent(applicationContext,AccountListActivity::class.java))
            }
            true
        }
        binding.fabPlus.setOnClickListener {
            startActivity(Intent(applicationContext, MovementFormActivity::class.java))
        }

        fragmentManager = supportFragmentManager
        openFragment(HomeFragment())
    }

    private fun openFragment(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}