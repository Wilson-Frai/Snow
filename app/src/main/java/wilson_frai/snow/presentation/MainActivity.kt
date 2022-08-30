package wilson_frai.snow.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.NavHostFragment
import wilson_frai.domain.models.WeatherModel
import wilson_frai.snow.R
import wilson_frai.snow.databinding.ActivityMainBinding
import wilson_frai.snow.databinding.FragmentFutureForecastBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_fragment) as NavHostFragment
        val navigationController = navHostFragment.navController
        binding.navigationPanel.setOnItemSelectedListener { item ->
            when(item.itemId) {

                R.id.action_now_fragment -> {

                    navigationController.navigate(R.id.action_futureForecastFragment_to_todayForecastFragment)
                    true
                }

                R.id.action_future_fragmetn -> {
                    navigationController.navigate(R.id.action_todayForecastFragment_to_futureForecastFragment)
                    true
                }

                else -> {
                    Log.i("Error", "MainActivity -> bottomNavigation -> not found item")
                    false
                }
            }
        }
    }

    companion object {
        private val weathersMutableLiveData = MutableLiveData<List<WeatherModel>>()
        val weathersLiveData: LiveData<List<WeatherModel>> = weathersMutableLiveData

        // City
        private val cityMutableLiveData = MutableLiveData<String>()
        val cityLiveData: LiveData<String> = cityMutableLiveData

        fun setWeather(weathers: List<WeatherModel>) {
            weathersMutableLiveData.value = weathers
        }

        fun setCity(city: String) {
            cityMutableLiveData.value = city
        }
    }
}