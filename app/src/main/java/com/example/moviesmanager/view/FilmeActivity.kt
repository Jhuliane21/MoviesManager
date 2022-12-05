package com.example.moviesmanager.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.moviesmanager.R
import com.example.moviesmanager.databinding.ActivityFilmeBinding
import com.example.moviesmanager.model.Constant.EXTRA_FILME
import com.example.moviesmanager.model.Constant.INVALID_FILME_ID
import com.example.moviesmanager.model.Constant.VIEW_FILME
import com.example.moviesmanager.model.Filme

class FilmeActivity : AppCompatActivity() {
    private val acb: ActivityFilmeBinding by lazy {
        ActivityFilmeBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(acb.root)

        val filmeRecebido = intent.getParcelableExtra<Filme>(EXTRA_FILME)
        filmeRecebido?.let{ _filmeRecebido ->
            with(acb) {
                with(_filmeRecebido) {
                    nomeEt.setText(nome)
                    duracaoEt.setText(duracao)
                    anoEt.setText(anoLancamento)
                    produtoraEt.setText(produtora)
                    notaEt.setText(nota)
                    if(assistido == "Sim"){
                        assistidoSw.isActivated
                    }

                }
            }
        }
        val viewFilme = intent.getBooleanExtra(VIEW_FILME, false)
        if (viewFilme) {
            acb.nomeEt.isEnabled = false
            acb.duracaoEt.isEnabled = false
            acb.anoEt.isEnabled = false
            acb.produtoraEt.isEnabled = false
            acb.notaEt.isEnabled = false
            acb.saveBt.visibility = View.GONE
        }

        acb.saveBt.setOnClickListener {
            var assistido = ""
            if(acb.assistidoSw.isActivated){
                assistido = "Sim"
            }else{
                assistido = "Não"
            }
            val filme = Filme(
                id = filmeRecebido?.id?: INVALID_FILME_ID,
                nome = acb.nomeEt.text.toString(),
                genero = acb.spinner1.toString(),
                duracao = acb.duracaoEt.text.toString(),
                assistido = assistido,
                anoLancamento = acb.anoEt.text.toString().toInt(),
                produtora = acb.produtoraEt.text.toString(),
                nota = acb.notaEt.text.toString().toInt()
            )
            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_FILME, filme)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}