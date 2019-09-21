package belajar.android.forumkita.api

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
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
        var okHttpClient = provideOkHttpClient(provideHttpLoggingInterceptor())
        var retrofit = provideRetrofit("http://my-json-server.typicode.com/",gson,okHttpClient)

        apiRepository = provideApiService(retrofit)
    }

    internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    /**
     * fungsi untuk membuat GSON
     */
    internal fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    /**
     * fungsi ApiRepository
     */
    internal fun provideApiService(retrofit: Retrofit): ApiRepository {
        return retrofit.create(ApiRepository::class.java)
    }

    internal fun provideRetrofit(baseUrl: String, gson: Gson, okHttpClient: OkHttpClient): Retrofit {
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
    internal fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val httpClient = OkHttpClient.Builder()

        httpClient.writeTimeout(30000, TimeUnit.SECONDS)
        httpClient.readTimeout(30000, TimeUnit.SECONDS)
        httpClient.connectTimeout(30000, TimeUnit.SECONDS)
        httpClient.addNetworkInterceptor(loggingInterceptor)

        return httpClient.build()
    }
}