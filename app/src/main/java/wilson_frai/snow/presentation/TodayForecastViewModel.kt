package wilson_frai.snow.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import wilson_frai.data.sharedpreferences.Implementation.CityImplementation
import wilson_frai.data.web.implementation.WeatherImplementation
import wilson_frai.domain.models.WeatherModel
import wilson_frai.domain.usecase.CityUseCase
import wilson_frai.domain.usecase.WeatherUseCase

class TodayForecastViewModel(application: Application) : AndroidViewModel(application) {
    private val weathersMutableLiveData = MutableLiveData<List<WeatherModel>>()
    val weathersLiveData: LiveData<List<WeatherModel>> = weathersMutableLiveData
    private val cityMutableLiveData = MutableLiveData<String>()
    val cityLiveData: LiveData<String> = cityMutableLiveData
    private val apiService = WeatherImplementation()
    private val weatherUseCase = WeatherUseCase(apiService)
    private val cityImplementation = CityImplementation(application.baseContext)
    private val cityUseCase = CityUseCase(cityImplementation)

    fun getWeather(city: String) {
        val scope = MainScope()
        scope.launch(Dispatchers.IO) {
            weathersMutableLiveData.postValue(weatherUseCase.getTodayWeatherForecast(city))
        }
    }

    fun saveCity(city: String) {
        cityUseCase.saveCity(city)
    }

    fun getCity(){
        val scope = MainScope()
        scope.launch(Dispatchers.IO) {
            cityMutableLiveData.value = cityUseCase.getCity()
        }
    }
}