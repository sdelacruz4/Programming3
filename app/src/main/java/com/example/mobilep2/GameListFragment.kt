package com.example.mobilep2

import android.content.Context
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
import android.widget.*
import androidx.core.view.ViewCompat.jumpDrawablesToCurrentState
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilep2.GameListFragment.Companion.newInstance
import java.util.*

private const val TAG = "GameListFragment"
private const val ARG_GAME_ID = "game_id"
/**
 * A simple [Fragment] subclass.
 * Use the [GameListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameListFragment : Fragment() {

    interface Callbacks{
        fun onGameSelected(gameId: UUID)
    }
    private var callbacks : Callbacks? = null

    private lateinit var gameRecyclerView: RecyclerView
    private var adapter: GameAdapter? = GameAdapter(emptyList())

    private val gameInfoViewModel: GameInfo by lazy {
        ViewModelProvider(this).get(GameInfo::class.java)
    }

    private lateinit var game: Game
    private lateinit var titleField: EditText
    private lateinit var dateButton: Button
    private lateinit var solvedCheckBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        game = Game()
        val gameId: UUID = arguments?.getSerializable(ARG_GAME_ID) as UUID
        gameInfoViewModel.loadGame(gameId)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_game_list, container, false)

        gameRecyclerView = view.findViewById(R.id.game_recycler_view) as RecyclerView
        gameRecyclerView.layoutManager = LinearLayoutManager(context)
        gameRecyclerView.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gameInfoViewModel.gameListLiveData.observe(
            viewLifecycleOwner,
            Observer { games ->
                games?.let {
                    Log.i(TAG, "Got Games ${games.size}")
                    this.game = game
                    updateUI(games)
                }
            })
    }

    private inner class GameHolder(view:View): RecyclerView.ViewHolder(view), View.OnClickListener {

        private lateinit var game: Game

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
            val fragment = MainFragment.newInstance(game.id)
            val fm = activity?.supportFragmentManager
            if (fm != null) {
                fm.beginTransaction()
                    .replace(R.id.fragment_main_container, fragment)
                    .commit()
            }

            callbacks?.onGameSelected(game.id)
            Toast.makeText(context, "${game.gameTitle} pressed!", Toast.LENGTH_SHORT).show()
        }

        fun bind(game: Game) {
            this.game = game
            gameTitleTextView.text = this.game.gameTitle
            gameDateTextView.text = this.game.date .toString()
            teamANameTextView.text = this.game.teamAName
            teamAScoreTextView.text = this.game.teamAScore.toString()
            teamBNameTextView.text = this.game.teamBName
            teamBScoreTextView.text = this.game.teamBScore.toString()

            if (game.teamAScore >= game.teamBScore) {
                //Checking to see if the team A score is greater than team B score to change image
                teamLogo.setImageResource(R.drawable.ic_launcher)
            } else if (game.teamBScore >= game.teamAScore) {
                teamLogo.setImageResource(R.drawable.lancher2)
            }
        }
    }

    private inner class GameAdapter(var games: List<Game>) : RecyclerView.Adapter<GameHolder>(){
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

    private fun updateUI(games: List<Game>){
        adapter = GameAdapter(games)
        gameRecyclerView.adapter = adapter

        titleField.setText(game.gameTitle)
        dateButton.text = game.date.toString()
        solvedCheckBox. apply{

        }
    }

    override fun onStop(){
        super.onStop()
        gameInfoViewModel.saveGame(game)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }
    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    companion object{
        fun newInstance(gameId: UUID): GameListFragment{
            val args = Bundle().apply{
                putSerializable(ARG_GAME_ID, gameId)
            }
            return GameListFragment().apply { arguments = args }
        }
    }
}

