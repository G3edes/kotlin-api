package com.example.clientapp.service

import com.example.clientapp.model.Cliente
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ClienteService {
    @POST("clientes")
    fun cadastrarCliente(@Body cliente: Cliente): Call<Cliente>
    @GET("clientes")
    fun listarTodos():Call<List<Cliente>>
}