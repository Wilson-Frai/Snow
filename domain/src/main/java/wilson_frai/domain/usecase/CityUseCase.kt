package wilson_frai.domain.usecase

import wilson_frai.domain.repositories.CityRepository

class CityUseCase(private val repository: CityRepository) {
    fun getCity() : String {
        return repository.getCity()
    }

    fun saveCity(city: String) {
        repository.saveCity(city)
    }
}