package com.example.moviesmanager.model.database

import android.content.ContentValues.TAG
import android.util.Log
import com.example.moviesmanager.model.dao.FilmeDao
import com.example.moviesmanager.model.entity.Filme
import com.example.moviesmanager.view.MainActivity
import com.google.firebase.database.*
import com.google.firebase.database.ktx.childEvents
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase


class FilmeDaoFirebase(mainActivity: MainActivity) : FilmeDao {
    private lateinit var dbReference : DatabaseReference

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
        var listaFilmes: MutableList<Filme> = mutableListOf<Filme>()
        dbReference = FirebaseDatabase.getInstance().getReference("Filmes")
        dbReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                listaFilmes.clear()
                if(snapshot.exists()) {
                    for (filme in snapshot.children) {
                        var filmeDados = filme.getValue(Filme::class.java)
                        listaFilmes.add(filmeDados!!)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })


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
        return 1
    }
}