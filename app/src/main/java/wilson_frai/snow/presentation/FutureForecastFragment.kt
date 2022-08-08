package wilson_frai.snow.presentation

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import wilson_frai.snow.R

class FutureForecastFragment : Fragment() {

    companion object {
        fun newInstance() = FutureForecastFragment()
    }

    private lateinit var viewModel: FutureForecastViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_future_forecast, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FutureForecastViewModel::class.java)
        // TODO: Use the ViewModel
    }

}