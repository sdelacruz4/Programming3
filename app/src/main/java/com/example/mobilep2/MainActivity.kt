package com.example.mobilep2

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.fragment.app.FragmentTransaction

import com.example.mobilep2.GameListFragment
import java.util.*

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), GameListFragment.Callbacks {

    override fun onGameSelected(gameId: UUID) {
        Log.d(TAG, "MainActivity.onGameSelected: $gameId")
        val fragment = MainFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_main_container, fragment)
            .addToBackStack(null)
            .commit()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val firstFragment = MainFragment() //MainFragment()
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_main_container)

        if (currentFragment == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_main_container, firstFragment)
                .commit()

        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

    }


}