package br.edu.ifsp.ads.pdm.mycontacts.adapter

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.moviesmanager.R
import com.example.moviesmanager.model.Filme

class FilmeAdapter(
    context: Context,
    private val filmeList: MutableList<Filme>
) : ArrayAdapter<Filme>(context, R.layout.activity_celula_filme, filmeList) {
    private data class CelulaFilmeHolder(val nomeTv: TextView, val generoTv: TextView)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val filme = filmeList[position]
        var filmeCelulaView = convertView
        if (filmeCelulaView == null) {
            filmeCelulaView =
                (context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                    R.layout.activity_celula_filme,
                    parent,
                    false
                )

            val celulaFilmeHolder = CelulaFilmeHolder(
                filmeCelulaView.findViewById(R.id.nomeTv),
                filmeCelulaView.findViewById(R.id.generoTv),
            )
            filmeCelulaView.tag = celulaFilmeHolder
        }

        with(filmeCelulaView?.tag as CelulaFilmeHolder) {
            nomeTv.text = filme.nome
            generoTv.text = filme.genero
        }

        return filmeCelulaView
    }
}