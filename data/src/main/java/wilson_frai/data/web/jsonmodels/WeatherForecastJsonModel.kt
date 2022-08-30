package wilson_frai.data.web.jsonmodels

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class WeatherForecastJsonModel (
    val list: List<HourlyWeather>?,
    val city: CityJsonModel?
)

@JsonClass(generateAdapter = true)
data class HourlyWeather(
    val main: MainJsonModel?,
    @Json(name = "weather")
    val weathers: List<WeatherJsonModel>?,
    val wind: WindJsonModel?,
    @Json(name = "data_txt")
    val data: String?
)

@JsonClass(generateAdapter = true)
data class MainJsonModel(
    @Json(name = "temp")
    val temperature: Double?,
    @Json(name = "feels_like")
    val temperatureFeels: Double?,
    val pressure: Int?,
    val humidity: Int?
)

@JsonClass(generateAdapter = true)
data class WeatherJsonModel(
    @Json(name = "main")
    val status: String?,
    val description: String?
)

@JsonClass(generateAdapter = true)
data class WindJsonModel(
    val speed: Double?,
    val deg: Int?
)

@JsonClass(generateAdapter = true)
data class CityJsonModel(
    val name: String?,
    val country: String?
)