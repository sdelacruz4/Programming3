Each Part for this project is in its own directory, /Part1/, /Part2/, etc.

Task 1:
We added a control sequence diagram to our report which highlights the transistions from MainActivity to Second Activity through onclicklisteners 
on the save and good game buttons respectively. This located in our WriteUp which can be seen for completion.

Task 2:
For this task we added intent to the Main Acitivty and the Second Activity to allow for changing between the two fragments. Ths can be seen in
both of their respective class files within the saveButton onClickListner implentation in the onCreate Function.

Task 3:
We refactored our code to work with fragments by taking the controller aspects of the Main Activity and Second Activity to their respective fragments files.
We also added the code for hosting the fragment and replacing the fragment for fragment transistions. These can be seen in these activities' onCreate Functions.

Task 4:
We added ViewModel functionality to the GameInfo class, where it hosts the 100 randomized ScoreView, or Game, objects.

Task 5:
We updated GameListFragment with Recycler Viewer. These can be seen within the GameListFragment file. The GameListFragment implements the GameHolder inner class for the RecyclerViewer.

Task 6:
We used a FrameLayout container called fragment_main_container in activity_main.xml to hold the child views of the RecycleViewer.

Task 7:
The RecycleViewer Adapter implementation is in GameListFragment. It is in an inner class called GameAdapter in GameListFragment.

Task 8: 
The RecyclerViewr is added to the main activity where the first fragment is initialized. For testing, we launched it with the RecyclerViewer
Fragment, GameListFragment.

Task 9:
For ConstraintLayout, we converted the list_item_game.xml from LinearLayout to ConstraintLayout. In MainFragment, we also added the functionality to
include winner and loser icons for each game list item.

Task 10:
We added the functionality for Data Persistance with Room in the Database folder and in GameRepository and GameApplication. For some reason,
we ran into issues with accessing the dummy data, but all our code runs without errors. Our immplementation seems to be able to access the database, but
but does not return any data from it, as our code shows in a debug call in logcat that no games were found. This might be a schema related issue, but as of 
writing this, we are unsure as to why we cant open the sqlite3 files and access the data from within the dummy database.

Task 11:
This feature is included in the previously mentioned code in Task 10, but we were unable to test because of our issues with the database.

Task 12: For part 6 regarding fragment navigation, we refactored the code to enable fragment navigation. With this section, we followed the video from the canvas site and created the ARG_GAME_ID constant in the repository and implemented fragment arguments such as val gameId: UUID = arguments?.getSerializable(ARG_GAME_ID) as UUID gameInfoViewModel.loadCrime(gameId). In this section we also had to implement the ViewModel and used GameInfo: ViewModel

Task 13: In order to implement fragment data passing, we had to create new data fields such as title, date, solvedCheckBox, etc. and use the gameDao and LiveData. In addition we implemented executors in the updateGame and addGame functions. We also had to update the updateUI code.
