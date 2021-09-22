package com.example.mobilep2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView

/**
 * A simple [Fragment] subclass.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val teamAScore = "com.example.mobilep2.scoreA"
private const val teamBScore = "com.example.mobilep2.scoreB"

class SecondFragment : Fragment() {

    private lateinit var ggbutton: Button
    private lateinit var imageview: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ggbutton = view.findViewById<Button>(R.id.gg_button)
        ggbutton.setOnClickListener { v ->
            appear(imageview)
        }

        imageview = view.findViewById<ImageView>(R.id.imageView)

        //Creates the actionbar to go back to main screen
        activity?.actionBar?.setDisplayHomeAsUpEnabled(true)

        var team_A_Score = activity?.intent?.getIntExtra(teamAScore, 0)
        var team_B_Score = activity?.intent?.getIntExtra(teamBScore, 0)


    }

    fun appear(view: android.view.View) {
        imageview.setImageResource(R.drawable.goodgame)
    }

}