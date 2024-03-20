package com.example.apiendereco.api

import com.example.apiendereco.model.Localizacao
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("{cep}/json")
    suspend fun buscarCep(@Path("cep") cep: String): Localizacao
}