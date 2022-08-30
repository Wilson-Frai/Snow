package wilson_frai.data.web.adapters

import com.squareup.moshi.*
import com.squareup.moshi.internal.Util
import wilson_frai.data.web.jsonmodels.WeatherForecastJsonModel
import wilson_frai.domain.models.WeatherModel

/*
class WeatherJsonAdapter {
    private val topLevelKeys = JsonReader.Options.of("list", "city")
    private val itemKeys = JsonReader.Options.of("main", "weather", "wind", "dt_txt")
    private val mainKeys = JsonReader.Options.of("temp", "feels_like", "pressure", "humidity")
    private val weatherKeys = JsonReader.Options.of("main", "descriptions")
    private val windKeys = JsonReader.Options.of("speed")

    @FromJson
    fun fromJson(reader: JsonReader): List<WeatherModel> {
        var heading: String? = null
        var body: String? = null
        var list: MutableList<WeatherModel> = mutableListOf()

        with(reader) {
            beginObject()
            while (hasNext()) {
                when (selectName(topLevelKeys)) {
                    -1 -> {
                        skipName()
                        skipValue()
                    }
                    0 -> {
                        reader.beginArray()
                        while (hasNext()) {
                            //val city: String,
                            var weather: String = ""
                            var description: String = ""
                            var temperature: Double = -100.0
                            var temperatureFeels: Double = -100.0
                            var pressure: Int = -100
                            var humidity: Int = -100
                            var windSpeed: Double = -100.0
                            var data: String = ""

                            when (selectName(itemKeys)) {
                                -1 -> {
                                    skipName()
                                    skipValue()
                                }
                                // main
                                0 -> {
                                    reader.beginObject()
                                    while (hasNext()) {
                                        when (selectName(mainKeys)) {
                                            -1 -> {
                                                skipName()
                                                skipValue()
                                            }
                                            0 -> {
                                                val text =
                                                    nextString() ?: throw Util.unexpectedNull(
                                                        "temperature",
                                                        "temp",
                                                        this
                                                    )
                                                temperature = text.toDouble()
                                            }
                                            1 -> {
                                                val text =
                                                    nextString() ?: throw Util.unexpectedNull(
                                                        "temperatureFeels",
                                                        "feels_like",
                                                        this
                                                    )
                                                temperatureFeels = text.toDouble()
                                            }
                                            2 -> {
                                                val text =
                                                    nextString() ?: throw Util.unexpectedNull(
                                                        "pressure",
                                                        "pressure",
                                                        this
                                                    )
                                                pressure = text.toInt()
                                            }
                                            3 -> {
                                                val text =
                                                    nextString() ?: throw Util.unexpectedNull(
                                                        "humidity",
                                                        "humidity",
                                                        this
                                                    )
                                                humidity = text.toInt()
                                            }
                                        }
                                    }
                                    reader.endObject()
                                }
                                // weather
                                // В json файле имеется список weather, в котором находится один объект.
                                1 -> {
                                    reader.beginArray()
                                    while (hasNext()) {
                                        reader.beginObject()
                                        while (hasNext()) {
                                            when (selectName(weatherKeys)) {
                                                -1 -> {
                                                    skipName()
                                                    skipValue()
                                                }
                                                0 -> {
                                                    val text =
                                                        nextString() ?: throw Util.unexpectedNull(
                                                            "status",
                                                            "main",
                                                            this
                                                        )
                                                    weather = text
                                                }
                                                1 -> {
                                                    val text =
                                                        nextString() ?: throw Util.unexpectedNull(
                                                            "description",
                                                            "description",
                                                            this
                                                        )
                                                    description = text
                                                }
                                            }
                                        }
                                        reader.endObject()
                                    }
                                    reader.endArray()
                                }
                                // wind
                                2 -> {
                                    reader.beginObject()
                                    while (hasNext()) {
                                        when (selectName(windKeys)) {
                                            -1 -> {
                                                skipName()
                                                skipValue()
                                            }
                                            0 -> {
                                                val text =
                                                    nextString() ?: throw Util.unexpectedNull(
                                                        "windSpeed",
                                                        "speed",
                                                        this
                                                    )
                                                windSpeed = text.toDouble()
                                            }
                                        }
                                    }
                                    reader.endObject()
                                }
                                3 -> {
                                    val text = nextString() ?: throw Util.unexpectedNull(
                                        "data",
                                        "dt_txt",
                                        this
                                    )
                                    data = text
                                }
                            }
                            list.add(WeatherModel(
                                city = "Test",
                                weather = weather,
                                description = description,
                                temperature = temperature,
                                pressure = pressure,
                                humidity = humidity,
                                windSpeed = windSpeed,
                                data = data
                            ))
                        }
                        reader.endArray()
                    }
                }
            }
            endObject()
        }
        return list
    }
}

/*
temperature = doubleAdapter.fromJson(reader) ?: throw JsonDataException("Non-null value 'name' was null at ${reader.path}")

                                                heading = nextString() ?: throw Util.unexpectedNull(
                                                    "heading",
                                                    "text",
                                                    this
                                                )
 */
 */