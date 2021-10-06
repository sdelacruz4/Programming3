This programming project is branched off from programming 2. All the works for this is through /Part7 branch

Task 1:
The cameras icons and button are both created with ImageView and ImageButtons

Task 2:
By launching the camera app, we were able to use implicit intents and and on the setOnClickListener for both button (Team A and Team B) 

Task 3:
In order to store images with FileProvider, we had to change the AndroidManifest.xml file and then go back to the code and initialize photoFile and send it through updatePhotoView function in order to take the photo that was taken to the correct path

Task 4:
To display the scaled-down bitmaps, we went on the updatePhotoView that takes all the images and initialize a bitmap and send it to val bitmap = getScaledBitmap(photoFile.path, requireActivity() as MainActivity)

Task 5:
We create the PictureUtils kotlin class in order to make sure the height and width where is accesses the BitmapFactory.Options in order to retrieve the correct sizes and orientation for the image

Task 6:
In OpenWeatherFetchr, I use a retrofit builder to construct the OpenWeather Api interface in the class.In the builder, I use the api url for openweather to display the information. Retrofit is also used in the OpenWeatherApi class as a get call to OpenWeather is made in it. These areas can be seen for assessment.

Task 7:
This task can be seen in all the newly changed files such as in onCreate and onCreateView  in the Main Fragment, OpenResponse class, OpenWeatherApi class, OpenWeatherFetchr class, WeatherItem data class, and  WeatherResponse class. These class handle certain parts of the api process, the fetch being called in OpenWeatherFetchr, the api call in OpenWeatherApi, OpenResponse  and WeatherResponse for the json collection, and the WeatherItem class for the json lists. These are areas for assessment.

Task 8: 
This task can be seen with the WeatherItem class, OpenWeatherFetchr class, Open Response and  WeatherResponse classes.WeatherItem contains the serialized name for an id. WeatherResponse has the serialized name for temperature. OpenResponse has the current JSON Object. The OpenWeatherFetchr class has the code using the GsonConverterFactory. These are areas for assessment.

Task 9:
Currently, the current weather for the location is being placed in the fragment_main.xml, added to the view in OnCreate in the owLiveData Observer. The data is added to the TextView for the id “Weather.” This an area of assessment for this task.


