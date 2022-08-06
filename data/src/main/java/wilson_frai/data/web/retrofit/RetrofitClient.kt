package wilson_frai.data.web.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    private val URL = "https://api.openweathermap.org"
    private val httpLoggingInterceptor = HttpLoggingInterceptor()

    private fun getRetrofitInstance(): Retrofit {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    public fun getApiService(): ApiService {
        return getRetrofitInstance().create(ApiService::class.java)
    }
}