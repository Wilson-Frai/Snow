package wilson_frai.snow.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import org.koin.androidx.viewmodel.ext.android.viewModel
import wilson_frai.domain.models.WeatherModel
import wilson_frai.snow.R
import wilson_frai.snow.presentation.adapters.WeatherAdapter
import wilson_frai.snow.databinding.FragmentTodayForecastBinding


class TodayForecastFragment : Fragment() {
    private lateinit var binding: FragmentTodayForecastBinding
    private val viewModel by viewModel<ForecastViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodayForecastBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPopupMenu(this.context)

        val adapter = WeatherAdapter()
        binding.todayForecastRecyclerView.adapter = adapter

        viewModel.weathersLiveData.observe(viewLifecycleOwner) { weathers ->
            if (weathers.isNotEmpty()) {
                adapter.weathers = weathers.subList(0, 7)
                val temperature = "${weathers[viewModel.hour/3].temperature.toString()}°"
                binding.todayForecastTemperature.text = temperature
                binding.todayForecastWeather.text = weathers[viewModel.hour/3].description
            } else {
                binding.todayForecastWeather.text = "Нет интернета или город не был найден."
            }
        }

        viewModel.cityLiveData.observe(viewLifecycleOwner) {
            viewModel.getWeather(it)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateWeather()
    }

    // ----

    private fun initPopupMenu(context: Context?) {
        val popupMenu = PopupMenu(context, binding.todayForecastButton)
        popupMenu.inflate(R.menu.popup_menu_additional_actions)
        popupMenu.setOnMenuItemClickListener { menu ->
            when (menu.itemId) {
                R.id.popup_find_city -> {
                    val dialogFragment = FindCityDialogFragment()
                    dialogFragment.show(childFragmentManager, FindCityDialogFragment.TAG)
                    true
                }
                R.id.popup_show_more_info -> {
                    // TODO - сделать DialogFragment с подробной информацией погоды на текущий момент.
                    true
                }
                else -> {
                    Log.i("Error", "CurrentWeatherFragment -> popupMenu -> not found item")
                    false
                }
            }
        }

        binding.todayForecastButton.setOnClickListener {
            popupMenu.show()
        }

        childFragmentManager.setFragmentResultListener(FindCityDialogFragment.REQUEST_KEY, viewLifecycleOwner) {
            _, bundle ->
            val city = bundle.getString(FindCityDialogFragment.KEY_RESPONSE).toString()
            viewModel.saveCity(city)
        }
    }
}