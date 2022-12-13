package com.example.moviesmanager.model.dao

import com.example.moviesmanager.model.entity.Filme

interface FilmeDao {
    fun addFilme(filme: Filme): String
    fun getFilme(id: String): Filme?
    fun getFilmes(): MutableList<Filme>
    fun updateFilme(filme: Filme): Int
    fun deleteFilme(id: String): Int
}