package wilson_frai.snow.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import wilson_frai.domain.models.WeatherModel
import wilson_frai.domain.usecase.CityUseCase
import wilson_frai.domain.usecase.WeatherUseCase
import java.text.SimpleDateFormat
import java.util.*


class ForecastViewModel(
        private val weatherUseCase: WeatherUseCase,
        private val cityUseCase: CityUseCase,
        private val connectionLiveData: LiveData<Boolean>
    ) : ViewModel() {
    // Internet Status
    val internetStatusLiveData: LiveData<Boolean> = connectionLiveData

    // Weathers
    private val weathersMutableLiveData = MutableLiveData<List<WeatherModel>>()
    val weathersLiveData: LiveData<List<WeatherModel>> = weathersMutableLiveData

    // City
    private val cityMutableLiveData = MutableLiveData<String>()
    val cityLiveData: LiveData<String> = cityMutableLiveData

    var hour: Int = 0
    var day = ""
    var days = mutableListOf<String>()

    private val scope = viewModelScope

    init {
        getTime()
        getDay()
    }

    fun getWeather(city: String) {
        if (city != "") {
            scope.launch(Dispatchers.IO) {
                weathersMutableLiveData.postValue(weatherUseCase.getTodayWeatherForecast(city))
            }
        }
    }

    fun updateWeather() {
        getCity()
        if (cityLiveData.value != null)
            getWeather(cityLiveData.value.toString())
    }

    fun saveCity(city: String) {
        cityUseCase.saveCity(city)
        cityMutableLiveData.value = city

    }

    fun getCity() {
        scope.launch(Dispatchers.IO) {
            cityMutableLiveData.postValue(cityUseCase.getCity())
        }
    }

    fun getTime() {
        val currentDate = Date()
        val time = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(currentDate).toString()
        hour = (time[0].toString().toInt() * 10) + time[1].toString().toInt()
    }

     fun getDay() {
         day = android.text.format.DateFormat.format("EEEE", Date()).toString()

         val calendar = Calendar.getInstance(Locale.UK)
         calendar[Calendar.DAY_OF_WEEK] = calendar.firstDayOfWeek
         val dateFormat = SimpleDateFormat("EEEE", Locale.UK)

         for (i in 0 until 7) {
             calendar.add(Calendar.DAY_OF_WEEK, 1)
             days.add(dateFormat.format(calendar.getTime()))
         }
     }
}