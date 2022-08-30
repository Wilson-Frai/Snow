package wilson_frai.snow.di

import androidx.lifecycle.LiveData
import org.koin.dsl.module
import wilson_frai.data.sharedpreferences.implementation.CityImplementation
import wilson_frai.data.web.ConnectionLiveData
import wilson_frai.data.web.implementation.WeatherImplementationWeb
import wilson_frai.domain.repositories.CityRepository
import wilson_frai.domain.repositories.WeatherRepositoryWeb

val dataModule = module {
    single<LiveData<Boolean>> {
        ConnectionLiveData(application = get())
    }

    single<WeatherRepositoryWeb> {
        WeatherImplementationWeb()
    }

    single<CityRepository> {
        CityImplementation(context = get())
    }
}