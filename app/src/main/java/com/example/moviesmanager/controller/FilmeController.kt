package com.example.moviesmanager.controller

import com.example.moviesmanager.model.entity.Filme
import com.example.moviesmanager.model.dao.FilmeDao
import com.example.moviesmanager.model.database.FilmeDaoFirebase
import com.example.moviesmanager.model.entity.FilmeFirebase
import com.example.moviesmanager.view.MainActivity

class FilmeController (mainActivity: MainActivity) {
    private val filmeDaoImpl2: FilmeDao = FilmeDaoFirebase(mainActivity)

    fun addFilme(filme: Filme) = filmeDaoImpl2.addFilme(filme)
    fun getFilme(id: String) = filmeDaoImpl2.getFilme(id)
    fun getFilmes(): MutableList<Filme> = filmeDaoImpl2.getFilmes()
    fun editFilme(filme: Filme) = filmeDaoImpl2.updateFilme(filme)
    fun removeFilme(id: String) = filmeDaoImpl2.deleteFilme(id)
}