package com.example.moviesmanager.model.entity

import android.os.Parcelable

import kotlinx.parcelize.Parcelize

@Parcelize

data class Filme(
    var id: String = "",
    var nome: String = "",
    var genero: String = "",
    var duracao: String = "",
    var assistido: String = "",
    var anoLancamento: Int? = null,
    var produtora: String = "",
    var nota: Int? = null,
): Parcelable
