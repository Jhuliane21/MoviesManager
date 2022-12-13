package com.example.moviesmanager.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.moviesmanager.databinding.ActivityFilmeBinding
import com.example.moviesmanager.model.Constant.EXTRA_FILME
import com.example.moviesmanager.model.Constant.INVALID_FILME_ID
import com.example.moviesmanager.model.Constant.VIEW_FILME
import com.example.moviesmanager.model.entity.Filme


class FilmeActivity : AppCompatActivity() {
    private val amb: ActivityFilmeBinding by lazy {
        ActivityFilmeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        val filmeRecebido = intent.getParcelableExtra<Filme>(EXTRA_FILME)
        filmeRecebido?.let{ _filmeRecebido ->
            with(amb) {
                with(_filmeRecebido) {
                    Log.v("teste", filmeRecebido.toString())
                    nomeEt.setText(nome)
                    duracaoEt.setText(duracao)
                    anoEt.setText(anoLancamento.toString())
                    produtoraEt.setText(produtora)
                    notaEt.setText(nota.toString())
                    if(assistido == "Sim"){
                        assistidoSw.isActivated
                    }

                }
            }
        }
        val viewFilme = intent.getBooleanExtra(VIEW_FILME, false)
        if (viewFilme) {
            amb.nomeEt.isEnabled = false
            amb.duracaoEt.isEnabled = false
            amb.anoEt.isEnabled = false
            amb.produtoraEt.isEnabled = false
            amb.notaEt.isEnabled = false
            amb.saveBt.visibility = View.GONE
        }

        amb.saveBt.setOnClickListener {
            val assistidoValor: String
            if(amb.assistidoSw.isActivated){
                assistidoValor = "Sim"
            }else{
                assistidoValor = "Não"
            }
                val movie = Filme(
                    id = filmeRecebido?.id?: INVALID_FILME_ID,
                    nome = amb.nomeEt.text.toString(),
                    genero = amb.spinner1.selectedItem.toString(),
                    duracao = amb.duracaoEt.text.toString(),
                    assistido = assistidoValor,
                    anoLancamento = amb.anoEt.text.toString().toInt(),
                    produtora = amb.produtoraEt.text.toString(),
                    nota = amb.notaEt.text.toString().toInt()

                )
                val resultIntent = Intent()
                resultIntent.putExtra(EXTRA_FILME, movie)
                setResult(RESULT_OK, resultIntent)
                finish()

            }
        }

}