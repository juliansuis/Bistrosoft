package com.juliansuis.bistrosoft.data.network

import com.juliansuis.bistrosoft.data.model.TimeDTO
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("now")
    suspend fun fetchTime(): Response<TimeDTO>
}
