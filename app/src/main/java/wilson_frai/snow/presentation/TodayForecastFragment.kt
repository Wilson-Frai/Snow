package wilson_frai.snow.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.dialog_fragment_find_city.*
import wilson_frai.domain.models.WeatherModel
import wilson_frai.snow.R
import wilson_frai.snow.adapter.WeatherAdapter
import wilson_frai.snow.databinding.FragmentTodayForecastBinding

class TodayForecastFragment : Fragment() {
    private lateinit var binding: FragmentTodayForecastBinding
    private lateinit var viewModel: TodayForecastViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodayForecastBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[TodayForecastViewModel::class.java]
        val weathers = viewModel.weathersLiveData
        val adapter = WeatherAdapter(day = 1)

        initPopupMenu(this.context)
        binding.todayForecastRecyclerView.adapter = adapter

        weathers.observe(viewLifecycleOwner) { weathers ->
            // TODO - Отображение той части, что идёт после текущего времени на устройстве.
            adapter.setData(weathers)
            binding.todayForecastRecyclerView.adapter?.notifyDataSetChanged()
            binding.todayForecastTemperature.text = weathers[0].temperature.toString()
            binding.todayForecastWeather.text = weathers[0].weather.toString()
        }

        // Для получение погоды при возвращение на фрагмент
        viewModel.cityLiveData.observe(viewLifecycleOwner) { city ->
            viewModel.getWeather(city)
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.getCity()
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
                    // TODO - сделать DialogFragment с подробной информацией.
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

        // Получение погоды при указания погоды.
        childFragmentManager.setFragmentResultListener(FindCityDialogFragment.REQUEST_KEY, viewLifecycleOwner) {
            _, bundle ->
            val city = bundle.getString(FindCityDialogFragment.KEY_RESPONSE).toString()
            viewModel.saveCity(city)
            viewModel.getWeather(city)
        }
    }
}