package wilson_frai.snow.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import wilson_frai.data.web.implementation.Implementation
import wilson_frai.domain.models.WeatherModel
import wilson_frai.domain.repositories.WeatherRepository
import wilson_frai.domain.usecase.WeatherUseCase

class TodayForecastViewModel : ViewModel() {
    private val weathersMutableLiveData = MutableLiveData<List<WeatherModel>>()
    val weathersLiveData: LiveData<List<WeatherModel>> = weathersMutableLiveData
    private val apiService = Implementation()
    private val useCase = WeatherUseCase(apiService)

    fun getWeather(city: String) {
        val scope = MainScope()
        scope.launch(Dispatchers.IO) {
            weathersMutableLiveData.postValue(useCase.getTodayWeatherForecast(city))
        }
    }

    fun saveCity(city: String) {
        // TODO - Сохранить город в ...
    }
}