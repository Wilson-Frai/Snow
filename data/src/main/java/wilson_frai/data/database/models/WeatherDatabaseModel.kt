package wilson_frai.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "weather_table")
class WeatherDatabaseModel (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo
    val city: String,
    @ColumnInfo
    val weather: String,
    @ColumnInfo
    val description: String,
    @ColumnInfo
    val temperature: Int,
    @ColumnInfo
    val pressure: Int,
    @ColumnInfo
    val humidity: Int,
    @ColumnInfo
    val windSpeed: Double,
    @ColumnInfo
    val data: String
) : Serializable