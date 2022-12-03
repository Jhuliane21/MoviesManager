package com.example.moviesmanager.model

import android.os.Parcelable

import kotlinx.parcelize.Parcelize

@Parcelize
data class Filme(
    var id: Int,
    var nome: String,
    var genero: String,
    var duracao: String,
    var assistido: String,
    var anoLancamento: Int,
    var produtora: String,
    var nota: Int,
): Parcelable
