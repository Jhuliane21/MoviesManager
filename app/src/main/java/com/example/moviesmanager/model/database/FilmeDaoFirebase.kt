package com.example.moviesmanager.model.database

import com.example.moviesmanager.model.dao.FilmeDao
import com.example.moviesmanager.model.entity.Filme
import com.example.moviesmanager.view.MainActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FilmeDaoFirebase(mainActivity: MainActivity) : FilmeDao {
    private lateinit var dbReference : DatabaseReference
    private lateinit var listaFilmes : MutableList<Filme>

    override fun addFilme(filme: Filme): String {
        dbReference = FirebaseDatabase.getInstance().getReference("Filmes")
        val empId = dbReference.push().key!!
        filme.id = empId
        dbReference.child(empId).setValue(filme)
        return filme.id
    }
    override fun getFilme(id: String): Filme? {
        TODO("Not yet implemented")
    }

    override fun getFilmes(): MutableList<Filme> {
        dbReference = FirebaseDatabase.getInstance().getReference("Filmes")
        listaFilmes.add(Filme("000000", "Crepusculo", "romance", "200", "Sim", 2000, "Disney", 10))
        return listaFilmes
    }

    override fun updateFilme(filme: Filme): Int {
        dbReference = FirebaseDatabase.getInstance().getReference("Filmes").child(filme.id)
        dbReference.setValue(filme)
        return 1
    }

    override fun deleteFilme(id: String): Int {
        dbReference = FirebaseDatabase.getInstance().getReference("Filmes")
        dbReference.child(id).removeValue();
        return id.toInt()
    }
}