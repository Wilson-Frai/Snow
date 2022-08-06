package wilson_frai.data.web.jsonmodel

import com.google.gson.annotations.SerializedName

data class WeatherForecastJsonModel (
    @SerializedName("list")
    val list: List<HourlyWeather>?,
    @SerializedName("city")
    val city: CityJsonModel?
)

data class HourlyWeather(
    @SerializedName("main")
    val main: MainJsonModel?,
    @SerializedName("weather")
    val weathers: List<WeatherJsonModel>?,
    @SerializedName("wind")
    val wind: WindJsonModel?,
    @SerializedName("data_txt")
    val data: String?
)

data class MainJsonModel(
    @SerializedName("temp")
    val temperature: Double?,
    @SerializedName("feels_like")
    val temperatureFeels: Double?,
    @SerializedName("pressure")
    val pressure: Int?,
    @SerializedName("humidity")
    val humidity: Int?
)

data class WeatherJsonModel(
    @SerializedName("main")
    val status: String?,
    @SerializedName("description")
    val description: String?
)

data class WindJsonModel(
    @SerializedName("speed")
    val speed: Double?,
    @SerializedName("deg")
    val deg: Int?

)

data class CityJsonModel(
    @SerializedName("name")
    val name: String?,
    @SerializedName("country")
    val country: String?
)