package com.example.moviesmanager.model

interface FilmeDao {
    fun addFilme(filme: Filme): Int
    fun getFilme(id: Int): Filme?
    fun getFilmes(): MutableList<Filme>
    fun updateFilme(filme: Filme): Int
    fun deleteFilme(id: Int): Int
}