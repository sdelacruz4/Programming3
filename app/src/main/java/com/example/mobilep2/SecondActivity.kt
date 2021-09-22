package com.example.mobilep2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

private const val teamAScore = "com.example.mobilep2.scoreA"
private const val teamBScore = "com.example.mobilep2.scoreB"

class SecondActivity : AppCompatActivity() {

    private lateinit var ggbutton: Button
    private lateinit var imageview: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        ggbutton = findViewById<Button>(R.id.gg_button)
        ggbutton.setOnClickListener { v ->
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        imageview = findViewById<ImageView>(R.id.imageView)

        //Creates the actionbar to go back to main screen
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var team_A_Score = intent.getIntExtra(teamAScore, 0)
        var team_B_Score = intent.getIntExtra(teamBScore, 0)


    }



    fun appear(view: android.view.View) {
        imageview.setImageResource(R.drawable.goodgame)
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