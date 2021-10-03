package com.example.mobilep2

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "GameListFragment"
/**
 * A simple [Fragment] subclass.
 * Use the [GameListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameListFragment : Fragment() {

    private lateinit var gameRecyclerView: RecyclerView
    private var adapter: GameAdapter? = null

    private val gameInfoViewModel: GameInfo by lazy {
        ViewModelProvider(this).get(GameInfo::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Total games: ${gameInfoViewModel.games.size}")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_game_list, container, false)

        gameRecyclerView = view.findViewById(R.id.game_recycler_view) as RecyclerView
        gameRecyclerView.layoutManager = LinearLayoutManager(context)

        updateUI()

        return view
    }

    private inner class GameHolder(view:View): RecyclerView.ViewHolder(view), View.OnClickListener {

        private lateinit var game: ScoreView

        val gameTitleTextView: TextView = itemView.findViewById(R.id.game_title)
        val gameDateTextView: TextView = itemView.findViewById(R.id.game_date)
        val teamANameTextView: TextView = itemView.findViewById(R.id.team1)
        val teamBNameTextView: TextView = itemView.findViewById(R.id.team2)
        val teamAScoreTextView: TextView = itemView.findViewById(R.id.team1Score)
        val teamBScoreTextView: TextView = itemView.findViewById(R.id.team2Score)

        private val teamLogo: ImageView = itemView.findViewById(R.id.winningLogo)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            //placeholder interaction
            Toast.makeText(context, "${game.getGameTitle()} pressed!", Toast.LENGTH_SHORT).show()
        }

        fun bind(game: ScoreView) {
            this.game = game
            gameTitleTextView.text = this.game.getGameTitle()
            gameDateTextView.text = this.game.getGameDate()
            teamANameTextView.text = this.game.getTeamAName()
            teamAScoreTextView.text = this.game.getTeamAScore().toString()
            teamBNameTextView.text = this.game.getTeamBName()
            teamBScoreTextView.text = this.game.getTeamBScore().toString()

            if (game.getTeamAScore() >= game.getTeamBScore()) {
                //Checking to see if the team A score is greater than team B score to change image
                teamLogo.setImageResource(R.drawable.ic_launcher)
            } else if (game.getTeamBScore() >= game.getTeamAScore()) {
                teamLogo.setImageResource(R.drawable.lancher2)
            }
        }
    }

    private inner class GameAdapter(var games: List<ScoreView>) : RecyclerView.Adapter<GameHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                : GameHolder {
            val view = layoutInflater.inflate(R.layout.list_item_game, parent, false)
            return GameHolder(view)
        }

        override fun onBindViewHolder(holder: GameHolder, position: Int) {
            val game = games[position]
            holder.bind(game)
        }

        override fun getItemCount() = games.size

    }

    private fun updateUI(){
        val games = gameInfoViewModel.games
        adapter = GameAdapter(games)
        gameRecyclerView.adapter = adapter
    }

    companion object{
        fun newInstance(): GameListFragment{
            return GameListFragment()
        }
    }
}

