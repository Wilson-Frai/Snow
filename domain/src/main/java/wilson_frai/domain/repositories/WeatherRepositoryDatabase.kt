package wilson_frai.domain.repositories

import wilson_frai.domain.models.WeatherModel


interface WeatherRepositoryDatabase {
    suspend fun insertWeather(weather: WeatherModel)
    suspend fun insertWeathers(weathers: List<WeatherModel>)
    suspend fun deleteWeather(weathers: WeatherModel)
    suspend fun getListWeather(): List<WeatherModel>
}
