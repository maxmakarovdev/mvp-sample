package ru.makarov.data

import io.reactivex.Observable
import retrofit2.http.GET

interface IDataApi {
    companion object {
        val BASE_URL = "https://www.mocky.io/v2/"
    }

    @GET("5a4d0c6232000036004324ba?mocky-delay=5000ms")
    fun getData(): Observable<Response>
}