package com.example.mobilep2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainFragment : Fragment() {
    private lateinit var three1button: Button
    private lateinit var three2button: Button
    private lateinit var two1button: Button
    private lateinit var two2button: Button
    private lateinit var free1button: Button
    private lateinit var free2button: Button
    private lateinit var resetbutton: Button
    private lateinit var savebutton: Button

    private lateinit var viewModel:ScoreView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = activity?.run {
            ViewModelProvider(this).get(ScoreView::class.java)
        } ?: throw Exception("Invalid Activity")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Three point button creation and onclick
        three1button = view.findViewById<Button>(R.id.three1_button)

        three1button.setOnClickListener { v ->
            threePointHandler("A")
        }

        three2button = view.findViewById<Button>(R.id.three2_button)

        three2button.setOnClickListener { v ->
            threePointHandler("B")
        }


        //Two point button creation and onclick
        two1button = view.findViewById<Button>(R.id.two1_button)

        two1button.setOnClickListener { v ->
            twoPointHandler("A")
        }

        two2button = view.findViewById<Button>(R.id.two2_button)

        two2button.setOnClickListener { v ->
            twoPointHandler("B")
        }


        //Free Throw button creation and onclick
        free1button = view.findViewById<Button>(R.id.free1_button)

        free1button.setOnClickListener { v ->
            onePointHandler("A")
        }

        free2button = view.findViewById<Button>(R.id.free2_button)

        free2button.setOnClickListener { v ->
            onePointHandler("B")
        }

        //Reset button creation and onclick
        resetbutton = view.findViewById<Button>(R.id.reset_button)

        resetbutton.setOnClickListener { v ->
            resetButtonHandler()
        }

        //Creating the second activity save button
        savebutton = view.findViewById<Button>(R.id.save_button)

        savebutton.setOnClickListener {
            val intent = Intent(context, SecondActivity::class.java)
            startActivity(intent)
        }



    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        checkWinner()
        displayA()
        displayB()
    }

    private fun threePointHandler(team: String) {
        val isOver: Int = checkWinner()
        if(isOver == 0 || isOver == 1){
            return
        }else {
            if (team === "A") {
                //call function to display points
                viewModel.updateTeamAScore(3)
                displayA()
            } else if (team === "B") {
                //call function to display points
                viewModel.updateTeamBScore(3)
                displayB()
            } else {
                Log.e("Team Error", "An invalid team was used")
            }
        }

    }

    private fun twoPointHandler(team: String) {
        val isOver: Int = checkWinner()
        if(isOver == 0 || isOver == 1){
            return
        }else {
            if (team === "A") {
                //call function to display points
                viewModel.updateTeamAScore(2)
                displayA()
            } else if (team === "B") {
                //call function to display points
                viewModel.updateTeamBScore(2)
                displayB()
            } else {
                Log.e("Team Error", "An invalid team was used")
            }
        }

    }

    private fun onePointHandler(team: String) {
        val isOver: Int = checkWinner()
        if(isOver == 0 || isOver == 1){
            return
        }else{
            if (team === "A") {
                //call function to display points
                viewModel.updateTeamAScore(1)
                displayA()
            } else if (team === "B") {
                //call function to display points
                viewModel.updateTeamBScore(1)
                displayB()
            } else {
                Log.e("Team Error", "An invalid team was used")
            }
        }

    }

    private fun resetButtonHandler() {
        viewModel.resetScores()
        if(requireView().findViewById<TextView>(R.id.teamAWin).visibility == View.VISIBLE){
            toggleVis(requireView().findViewById<TextView>(R.id.teamAWin))
        }
        else if(requireView().findViewById<TextView>(R.id.teamBWin).visibility == View.VISIBLE){
            toggleVis(requireView().findViewById<TextView>(R.id.teamBWin))
        }
        displayA()
        displayB()
    }

    private fun displayA(){
        val scoreViewModel: TextView = requireView().findViewById<TextView>(R.id.scoreA)
        scoreViewModel.text = viewModel.getTeamAScore().toString()

    }

    private fun displayB(){
        val scoreViewModel: TextView = requireView().findViewById<TextView>(R.id.scoreB)
        scoreViewModel.text = viewModel.getTeamBScore().toString()
    }

    private fun checkWinner(): Int{
        if(viewModel.getTeamAScore() >= 25){
            //toggle visibility for a
            if(requireView().findViewById<TextView>(R.id.teamAWin).visibility == View.INVISIBLE) {
                toggleVis(requireView().findViewById<TextView>(R.id.teamAWin))
            }
            return 1
        }
        else if(viewModel.getTeamBScore() >= 25){
            //toggle visibility for b
            if(requireView().findViewById<TextView>(R.id.teamBWin).visibility == View.INVISIBLE) {
                toggleVis(requireView().findViewById<TextView>(R.id.teamBWin))
            }
            return 0
        }
        else{
            return -1
        }

    }

    private fun toggleVis(item: TextView){
        if(item.visibility == View.VISIBLE){
            item.visibility = View.INVISIBLE
        }else{
            item.visibility = View.VISIBLE
        }
    }



}