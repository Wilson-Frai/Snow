package wilson_frai.data.sharedpreferences.implementation

import android.content.Context
import android.util.Log
import wilson_frai.domain.repositories.CityRepository


class CityImplementation(private val context: Context) : CityRepository {
    private val SHARED_PREFERENCES_NAME = "shared_preferences_name"
    private val KEY_CITY = "city"
    private val DEFAULT_CITY = ""
    private val sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    override fun saveCity(city: String) {
        sharedPreferences.edit().putString(KEY_CITY, city).apply()
    }

    override fun getCity(): String {
        return sharedPreferences.getString(KEY_CITY, DEFAULT_CITY) ?: DEFAULT_CITY
    }

}