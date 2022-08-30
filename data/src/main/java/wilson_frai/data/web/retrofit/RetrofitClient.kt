package wilson_frai.data.web.retrofit

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object RetrofitInstance {
    private val URL = "https://api.openweathermap.org"
    private val httpLoggingInterceptor = HttpLoggingInterceptor()
    init {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val apiService = retrofit.create(ApiService::class.java)
}