package wilson_frai.data.web.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import wilson_frai.data.web.jsonmodel.WeatherForecastJsonModel



interface ApiService {
    // Запрос по городам.
    @GET("data/2.5/forecast?units=metric")
    fun getHourlyWeatherForecast(
        @Query("q") city: String,
        @Query("appid") key: String
    ) : Call<WeatherForecastJsonModel>

    // Запрос по координатам
    @GET("data/2.5/forecast?&units=metric"
    )
    fun getHourlyWeatherForecast(
        @Query("lat")  latitude: Double,
        @Query("lon") longitude:Double,
        @Query("appid") key: String
    ) : Call<WeatherForecastJsonModel>

    @GET("data/2.5/weather?units=metric")
    fun getCurrentWeatherForecast(
        @Query("q") city: String,
        @Query("appid") key: String
    ) : Call<WeatherForecastJsonModel>

    // Запрос по координатам
    @GET("data/2.5/weather?&units=metric"
    )
    fun getCurrentWeatherForecast(
        @Query("lat")  latitude: Double,
        @Query("lon") longitude:Double,
        @Query("appid") key: String
    ) : Call<WeatherForecastJsonModel>
}