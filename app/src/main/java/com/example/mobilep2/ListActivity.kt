package com.example.mobilep2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentTransaction
import java.util.*
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity

import android.os.PersistableBundle
import android.widget.Button

import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

import com.example.mobilep2.GameListFragment
import java.util.*


private const val TAG = "ListActivity"

class ListActivity : AppCompatActivity(), GameListFragment.Callbacks  {

    override fun onGameSelected(gameId: UUID) {
        Log.d(TAG, "ListActivity.onCrimeSelected: $gameId")
        val fragment = MainFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_main_container, fragment)
            .commit()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        if(savedInstanceState == null){
            val firstFragment = GameListFragment.newInstance()//MainFragment()

            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            ft.add(R.id.fragment_main_container, firstFragment)
            ft.commit()
        }

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

    }


}