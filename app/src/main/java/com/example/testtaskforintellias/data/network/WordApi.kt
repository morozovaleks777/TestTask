package com.example.testtaskforbootcamp.data.network

import com.example.testtaskforintellias.data.network.WordResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface WordApi {

    @GET("{word}")
    suspend fun getWord(@Path("word") word: String): List<WordResponse.WordResponseItem>

}
