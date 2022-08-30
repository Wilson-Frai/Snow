package wilson_frai.domain.repositories

import wilson_frai.domain.models.WeatherModel

interface WeatherRepositoryWeb {
    fun getTodayWeatherForecast(city: String): List<WeatherModel>
    fun getTodayWeatherForecast(latitude:Double, longitude: Double): List<WeatherModel>

    //fun getHourlyWeatherForecast(city: String, language: String): List<WeatherModel>
    //fun getHourlyWeatherForecast(latitude:Double, longitude: Double, language: String): List<WeatherModel>
}