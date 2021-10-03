package com.example.mobilep2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction






class SecondActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        if(savedInstanceState == null){
            val firstFragment = SecondFragment()

            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            ft.add(R.id.fragment_second_container, firstFragment)
            ft.commit()
        }

    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        //Attributes for Toast
        val toastText = "Great Game Teams!"
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(applicationContext, toastText, duration)
        toast.show()
        return true
    }
}