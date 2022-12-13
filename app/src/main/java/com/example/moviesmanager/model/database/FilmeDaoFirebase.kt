package com.example.moviesmanager.model.database

import com.example.moviesmanager.model.dao.FilmeDao
import com.example.moviesmanager.model.entity.Filme

class FilmeDaoFirebase : FilmeDao {
    override fun addFilme(filme: Filme): Int {
        TODO("Not yet implemented")
    }

    override fun getFilme(id: Int): Filme? {
        TODO("Not yet implemented")
    }

    override fun getFilmes(): MutableList<Filme> {
        TODO("Not yet implemented")
    }

    override fun updateFilme(filme: Filme): Int {
        TODO("Not yet implemented")
    }

    override fun deleteFilme(id: Int): Int {
        TODO("Not yet implemented")
    }
}