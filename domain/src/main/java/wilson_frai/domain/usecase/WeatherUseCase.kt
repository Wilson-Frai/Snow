package wilson_frai.domain.usecase

import wilson_frai.domain.models.WeatherModel
import wilson_frai.domain.repositories.WeatherRepositoryWeb

class WeatherUseCase(private val repository: WeatherRepositoryWeb) {

    fun getTodayWeatherForecast(city: String): List<WeatherModel> {
        return repository.getTodayWeatherForecast(city)
    }
}