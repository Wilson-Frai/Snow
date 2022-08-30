package wilson_frai.snow.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.viewModel
import wilson_frai.snow.databinding.FragmentFutureForecastBinding
import wilson_frai.snow.presentation.adapters.WeathersAdapter


class FutureForecastFragment : Fragment() {
    private val viewModel: ForecastViewModel by viewModel<ForecastViewModel>()
    private lateinit var binding: FragmentFutureForecastBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFutureForecastBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCity()
        viewModel.cityLiveData.observe(viewLifecycleOwner) {
            viewModel.getWeather(it)
        }
        viewModel.weathersLiveData.observe(viewLifecycleOwner) {
            val adapter = WeathersAdapter(view.context, viewModel.days)
            adapter.weathers = it
            binding.forecastFragment.adapter = adapter
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateWeather()
    }
}