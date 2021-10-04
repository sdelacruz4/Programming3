package com.example.mobilep2

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import java.io.File
import java.util.*

private const val ARG_GAME_ID = "game_id"
private const val REQUEST_CONTACT = 1
private const val REQUEST_PHOTO = 2


class MainFragment : Fragment() {
    private lateinit var three1button: Button
    private lateinit var three2button: Button
    private lateinit var two1button: Button
    private lateinit var two2button: Button
    private lateinit var free1button: Button
    private lateinit var free2button: Button
    private lateinit var resetbutton: Button
    private lateinit var savebutton: Button
    private lateinit var aTeamImage: ImageView
    private lateinit var bTeamImage: ImageView
    private lateinit var aTeamButton: ImageButton
    private lateinit var bTeamButton: ImageButton
    private lateinit var photoUri: Uri
    private lateinit var photoFile: File


    private lateinit var viewModel:ScoreView
    private val TAG = "MainFragment"



    companion object {
        fun newInstance(gameId: UUID): MainFragment {
            val args = Bundle().apply {
                putSerializable(ARG_GAME_ID, gameId)
            }
            return MainFragment().apply {
                arguments = args
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = activity?.run {
            ViewModelProvider(this).get(ScoreView::class.java)
        } ?: throw Exception("Invalid Activity")

        val gameID: UUID = arguments?.getSerializable(ARG_GAME_ID) as UUID
        Log.d(TAG, "args bundle crime ID: $gameID")
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

        aTeamButton = view.findViewById<ImageButton>(R.id.aTeamButton)
        aTeamButton.apply{
            val packageManager: PackageManager = requireActivity().packageManager

            val captureImage = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val resolvedActivity: ResolveInfo? =
                packageManager.resolveActivity(captureImage, PackageManager.MATCH_DEFAULT_ONLY)

            Log.d(TAG, "${resolvedActivity} is null?")
            if(resolvedActivity == null){
                isEnabled = false
            }

            setOnClickListener{
                captureImage.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)

                val cameraActivities: List<ResolveInfo> =
                    packageManager.queryIntentActivities(captureImage,
                        PackageManager.MATCH_DEFAULT_ONLY)
                for (cameraActivity in cameraActivities){
                    requireActivity().grantUriPermission(
                        cameraActivity.activityInfo.packageName,
                        photoUri,
                        Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                    )
                }
                startActivityForResult(captureImage, REQUEST_PHOTO)
            }
        }

        bTeamButton = view.findViewById<ImageButton>(R.id.bTeamButton)
        bTeamButton.apply{
            val packageManager: PackageManager = requireActivity().packageManager

            val captureImage = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val resolvedActivity: ResolveInfo? =
                packageManager.resolveActivity(captureImage, PackageManager.MATCH_DEFAULT_ONLY)

            Log.d(TAG, "${resolvedActivity} is null?")
            if(resolvedActivity == null){
                isEnabled = false
            }

            setOnClickListener{
                captureImage.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)

                val cameraActivities: List<ResolveInfo> =
                    packageManager.queryIntentActivities(captureImage,
                        PackageManager.MATCH_DEFAULT_ONLY)
                for (cameraActivity in cameraActivities){
                    requireActivity().grantUriPermission(
                        cameraActivity.activityInfo.packageName,
                        photoUri,
                        Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                    )
                }
                startActivityForResult(captureImage, REQUEST_PHOTO)
            }
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when{
            resultCode != Activity.RESULT_OK -> return
            requestCode == REQUEST_CONTACT && data != null ->{
                val contactUri: Uri? = data.data
                val queryFields = arrayOf(ContactsContract.Contacts.DISPLAY_NAME)
                val cursor = contactUri?.let { requireActivity().contentResolver.query(it, queryFields, null, null, null) }
                cursor?.use{
                    if(it.count == 0){
                        return
                    }
                    it.moveToFirst()
                    val suspect = it.getString(0) //may not need?
                }
            }
            requestCode == REQUEST_PHOTO ->{
                requireActivity().revokeUriPermission(photoUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                updatePhotoView()
            }
        }
    }

    private fun updatePhotoView() {
        if(photoFile.exists()){
            val bitmap = getScaledBitmap(photoFile.path, requireActivity() as MainActivity)
            aTeamImage.setImageBitmap(bitmap)
            bTeamImage.setImageBitmap(bitmap)
        } else {
            aTeamImage.setImageDrawable(null)
            bTeamImage.setImageDrawable(null)
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