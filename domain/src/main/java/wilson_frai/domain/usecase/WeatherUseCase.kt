package wilson_frai.domain.usecase

import wilson_frai.domain.models.WeatherModel
import wilson_frai.domain.repositories.WeatherRepository

class WeatherUseCase(private val repository: WeatherRepository) {

    fun getTodayWeatherForecast(city: String): List<WeatherModel> {
        return repository.getTodayWeatherForecast(city)
    }
}