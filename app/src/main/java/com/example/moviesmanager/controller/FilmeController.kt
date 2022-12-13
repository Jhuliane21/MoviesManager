package com.example.moviesmanager.controller

import com.example.moviesmanager.model.entity.Filme
import com.example.moviesmanager.model.dao.FilmeDao
import com.example.moviesmanager.model.database.FilmeDaoSqlite
import com.example.moviesmanager.view.MainActivity

class FilmeController (mainActivity: MainActivity) {
    private val filmeDaoImpl: FilmeDao = FilmeDaoSqlite(mainActivity)

    fun addFilme(filme: Filme) = filmeDaoImpl.addFilme(filme)
    fun getFilme(id: Int) = filmeDaoImpl.getFilme(id)
    fun getFilmes() = filmeDaoImpl.getFilmes()
    fun editFilme(filme: Filme) = filmeDaoImpl.updateFilme(filme)
    fun removeFilme(id: Int) = filmeDaoImpl.deleteFilme(id)
}