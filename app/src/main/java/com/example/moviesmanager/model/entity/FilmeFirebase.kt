package com.example.moviesmanager.model.entity

data class FilmeFirebase(
    var id: String,
    var nome: String,
    var genero: String,
    var duracao: String,
    var assistido: String,
    var anoLancamento: Int,
    var produtora: String,
    var nota: Int,
)
