package wilson_frai.data.web.implementation

import wilson_frai.data.web.jsonmodels.WeatherForecastJsonModel
import wilson_frai.data.web.retrofit.RetrofitInstance
import wilson_frai.domain.models.WeatherModel
import wilson_frai.domain.repositories.WeatherRepositoryWeb
import java.lang.Exception


class WeatherImplementationWeb: WeatherRepositoryWeb {
    private val API_KEY = "94af117bd5336cfc1ff9fa18116a1df7"
    private val apiService = RetrofitInstance.apiService

    override fun getTodayWeatherForecast(city: String): List<WeatherModel> {
        // В процессе изучения поимки ошибок и их обработки, поэтому сейчас тут простой try-catсh.
        // После понимания правил и способов разбора ошибок, поменяю на более правильный способ.
        return try {
            val result = apiService.getHourlyWeatherForecast(city, API_KEY).execute().body()
            if (result != null)
                convertToWeatherModelList(result)
            else
                emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    override fun getTodayWeatherForecast(latitude: Double, longitude: Double, ): List<WeatherModel> {
        val result = apiService.getHourlyWeatherForecast(latitude = latitude, longitude = longitude, API_KEY).execute().body()
        return if (result != null)
            convertToWeatherModelList(result)
        else
            emptyList()
    }

    // Приходится переводить данные в нормальный класс, так как структура json не удобная.
    // На данный момент оставил такой вариант,
    // так как в процессе изучения по созданию модели по мере чтения json файла.
    private fun convertToWeatherModelList(jsonModel: WeatherForecastJsonModel): List<WeatherModel> {
        val result = mutableListOf<WeatherModel>()

        for (index in 0 until 40) {
            result.add(index, WeatherModel(
                city = jsonModel.city?.name ?: "",
                weather = jsonModel.list?.get(index)?.weathers?.get(0)?.status ?: "",
                description = jsonModel.list?.get(index)?.weathers?.get(0)?.description ?: "",
                temperature = jsonModel.list?.get(index)?.main?.temperature?.toInt() ?: -100,
                pressure = jsonModel.list?.get(index)?.main?.pressure ?: -100,
                humidity = jsonModel.list?.get(index)?.main?.humidity ?: -100,
                windSpeed = jsonModel.list?.get(index)?.wind?.speed ?: - 100.0,
                data = jsonModel.list?.get(index)?.data ?: ""
            ))
        }
        return result
    }
}