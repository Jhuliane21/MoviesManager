package com.example.moviesmanager.model.database

import android.widget.Toast
import com.example.moviesmanager.model.dao.FilmeDao
import com.example.moviesmanager.model.entity.Filme
import com.example.moviesmanager.view.MainActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FilmeDaoFirebase(mainActivity: MainActivity) : FilmeDao {
    private lateinit var dbReference : DatabaseReference

    override fun addFilme(filme: Filme): Int {
        dbReference = FirebaseDatabase.getInstance().getReference("Filmes")
        val empId = dbReference.push().key!!
        dbReference.child(empId).setValue(filme)
        return filme.id
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
        dbReference = FirebaseDatabase.getInstance().getReference("Filmes")
        val empId = dbReference.push().key!!
        dbReference.child(empId).removeValue();
        return empId.toString().toInt()
    }
}