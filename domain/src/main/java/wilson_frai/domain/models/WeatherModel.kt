package wilson_frai.domain.models

data class WeatherModel(
    val city: String,
    val weather: String,
    val description: String,
    val temperature: Int,
    val pressure: Int,
    val humidity: Int,
    val windSpeed: Double,
    val data: String
)