package wilson_frai.data.database.dao

import androidx.room.*
import wilson_frai.data.database.models.WeatherDatabaseModel
import wilson_frai.domain.models.WeatherModel


@Dao
interface WeatherDao {
    @Insert
    suspend fun insert(weather: WeatherDatabaseModel)

    @Delete
    suspend fun delete(weather: WeatherDatabaseModel)

    @Query("SELECT * from weather_table")
    fun getListWeather(): List<WeatherModel>
}