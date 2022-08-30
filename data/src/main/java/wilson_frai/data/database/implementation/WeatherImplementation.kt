package wilson_frai.data.database.implementation

import wilson_frai.data.database.dao.WeatherDao
import wilson_frai.data.database.models.WeatherDatabaseModel
import wilson_frai.domain.models.WeatherModel
import wilson_frai.domain.repositories.WeatherRepositoryDatabase


class WeatherImplementation(private val weatherDao: WeatherDao): WeatherRepositoryDatabase {
    override suspend fun insertWeather(weather: WeatherModel) {
        weatherDao.insert(WeatherDatabaseModel(
            city = weather.city,
            weather = weather.weather,
            description = weather.description,
            temperature = weather.temperature,
            pressure = weather.pressure,
            humidity = weather.humidity,
            windSpeed = weather.windSpeed,
            data = weather.data
        ))
    }

    override suspend fun insertWeathers(weathers: List<WeatherModel>) {
        for (i in weathers.indices)
            weatherDao.insert(WeatherDatabaseModel(
                city = weathers[i].city,
                weather = weathers[i].weather,
                description = weathers[i].description,
                temperature = weathers[i].temperature,
                pressure = weathers[i].pressure,
                humidity = weathers[i].humidity,
                windSpeed = weathers[i].windSpeed,
                data = weathers[i].data
            ))
    }

    override suspend fun deleteWeather(weather: WeatherModel) {
        weatherDao.delete(WeatherDatabaseModel(
            city = weather.city,
            weather = weather.weather,
            description = weather.description,
            temperature = weather.temperature,
            pressure = weather.pressure,
            humidity = weather.humidity,
            windSpeed = weather.windSpeed,
            data = weather.data
        ))
    }

    override suspend fun getListWeather(): List<WeatherModel> {
        val result = weatherDao.getListWeather()
        val weathers = mutableListOf<WeatherModel>()

        result.forEach {
            weathers.add(WeatherModel(
                city = it.city,
                weather = it.weather,
                description = it.description,
                temperature = it.temperature,
                pressure = it.pressure,
                humidity = it.humidity,
                windSpeed = it.windSpeed,
                data = it.data
            ))
        }

        return weathers
    }
}