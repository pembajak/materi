package belajar.android.forumkita.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiService {

    var apiRepository : ApiRepository

    init {

        var gson = provideGson()
        var okHttpClient = provideOkHttpClient()
        var retrofit = provideRetrofit("https://raw.githubusercontent.com/",gson,okHttpClient)

        apiRepository = provideApiService(retrofit)
    }

    /**
     * fungsi untuk Parsing json
     */
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    /**
     * Membuat Retrofit
     */
    fun provideApiService(retrofit: Retrofit): ApiRepository {
        return retrofit.create(ApiRepository::class.java)
    }

    fun provideRetrofit(baseUrl: String, gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()
    }


    /**
     * fungsi provideOkHttpClient
     */
    fun provideOkHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()

        httpClient.writeTimeout(30000, TimeUnit.SECONDS)
        httpClient.readTimeout(30000, TimeUnit.SECONDS)
        httpClient.connectTimeout(30000, TimeUnit.SECONDS)

        return httpClient.build()
    }
}