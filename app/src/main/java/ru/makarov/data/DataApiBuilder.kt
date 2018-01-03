package ru.makarov.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

object DataApiBuilder {

    private val HTTP_LOG_TAG = "OkHttp"
    private val SOCKET_READ_TIMEOUT = 15L
    private val SOCKET_CONNECT_TIMEOUT = 15L

    fun createAPI(): IDataApi {
        val httpClient = OkHttpClient.Builder().apply {
            connectTimeout(SOCKET_CONNECT_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(SOCKET_READ_TIMEOUT, TimeUnit.SECONDS)
            addInterceptor(HttpLoggingInterceptor { Timber.tag(HTTP_LOG_TAG).d(it) }.apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }.build()

        val retrofitBuilder = Retrofit.Builder()
                .baseUrl(IDataApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)

        return retrofitBuilder.build().create(IDataApi::class.java)
    }
}
