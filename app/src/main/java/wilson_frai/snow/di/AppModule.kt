package wilson_frai.snow.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import wilson_frai.snow.presentation.ForecastViewModel

val appModule = module {
    viewModel<ForecastViewModel>{
        ForecastViewModel(
            weatherUseCase = get(),
            cityUseCase = get(),
            connectionLiveData = get()
        )
    }
}