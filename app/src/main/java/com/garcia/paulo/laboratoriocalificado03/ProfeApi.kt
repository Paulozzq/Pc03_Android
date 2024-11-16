package com.garcia.paulo.laboratoriocalificado03

import retrofit2.http.GET

interface ProfeApi {
    @GET("list/teacher")
    suspend fun getProfesores(): ProfeListResponse
}