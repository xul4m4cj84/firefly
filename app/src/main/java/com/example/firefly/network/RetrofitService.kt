package com.example.firefly.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_URL = "https://opendata.cwb.gov.tw/api/v1/rest/datastore/"
private const val API_KEY = "CWB-07FAC0FB-49D5-45A2-BCE7-1F7796B48427"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(API_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface RetrofitService {
    @GET("F-C0032-001?Authorization=$API_KEY")
    suspend fun getAppData(@Query("locationName") location: String): WeatherData
}

//global singleton object
object GetService {
    val retrofitService: RetrofitService by lazy {
        retrofit.create(RetrofitService::class.java)
    }
}