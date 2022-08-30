package wilson_frai.snow.di


import org.koin.dsl.module
import wilson_frai.domain.usecase.CityUseCase
import wilson_frai.domain.usecase.WeatherUseCase

val domainModule = module {
    factory<WeatherUseCase> {
        WeatherUseCase(repository = get())
    }
    factory<CityUseCase> {
        CityUseCase(repository = get())
    }
}