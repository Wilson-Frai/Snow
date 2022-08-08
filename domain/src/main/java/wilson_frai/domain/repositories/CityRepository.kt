package wilson_frai.domain.repositories


interface CityRepository {
    fun saveCity(city: String)
    fun getCity() : String
}