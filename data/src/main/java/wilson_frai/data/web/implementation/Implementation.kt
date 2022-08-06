package wilson_frai.data.web.implementation

import wilson_frai.data.web.jsonmodel.WeatherForecastJsonModel
import wilson_frai.data.web.retrofit.RetrofitClient
import wilson_frai.domain.models.WeatherModel
import wilson_frai.domain.repositories.WeatherRepository

class Implementation() : WeatherRepository {
    // TODO - Удалить ключ перед публикацией.
    private val API_KEY = "94af117bd5336cfc1ff9fa18116a1df7"
    private val apiService = RetrofitClient().getApiService()

    override fun getTodayWeatherForecast(city: String): List<WeatherModel> {
        val result = apiService.getHourlyWeatherForecast(city, API_KEY).execute().body()

        return if (result != null)
            convertToWeatherModelList(result)
        else
            // TODO - Обработать этот момент.
            emptyList()
    }

    override fun getTodayWeatherForecast(latitude: Double, longitude: Double, ): List<WeatherModel> {
        val result = apiService.getHourlyWeatherForecast(latitude, longitude, API_KEY).execute().body()

        return if (result != null)
            convertToWeatherModelList(result)
        else
            // TODO - Обработать этот момент.
            emptyList()
    }

    private fun convertToWeatherModelList(jsonModel: WeatherForecastJsonModel): List<WeatherModel> {
        val result = mutableListOf<WeatherModel>()

        for (index in 0..8) {
            result.add(index, WeatherModel(
                city = jsonModel.city?.name,
                weather = jsonModel.list?.get(index)?.weathers?.get(0)?.status,
                description = jsonModel.list?.get(index)?.weathers?.get(0)?.description,
                temperature = jsonModel.list?.get(index)?.main?.temperature,
                temperatureFeels = jsonModel.list?.get(index)?.main?.temperatureFeels,
                pressure = jsonModel.list?.get(index)?.main?.pressure,
                humidity = jsonModel.list?.get(index)?.main?.humidity,
                windSpeed = jsonModel.list?.get(index)?.wind?.speed,
                data = jsonModel.list?.get(index)?.data
            ))
        }

        return result
    }
}