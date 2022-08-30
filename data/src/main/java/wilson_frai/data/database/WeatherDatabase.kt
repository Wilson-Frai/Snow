package wilson_frai.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import wilson_frai.data.database.dao.WeatherDao
import wilson_frai.domain.models.WeatherModel


@Database(entities = [WeatherModel::class], version = 1)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun getWeatherDao(): WeatherDao

    companion object {
        private var database: WeatherDatabase?= null

        @Synchronized
        fun getInstance(context: Context): WeatherDatabase {
            return if (database == null) {
                database = Room.databaseBuilder(context, WeatherDatabase::class.java, "weather_database").build()
                database as WeatherDatabase
            } else {
                database as WeatherDatabase
            }
        }
    }
}