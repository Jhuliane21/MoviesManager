package com.example.moviesmanager.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.moviesmanager.databinding.ActivityFilmeBinding
import com.example.moviesmanager.model.Constant.EXTRA_FILME
import com.example.moviesmanager.model.Constant.INVALID_FILME_ID
import com.example.moviesmanager.model.Constant.VIEW_FILME
import com.example.moviesmanager.model.entity.Filme
import com.google.firebase.database.DatabaseReference


class FilmeActivity : AppCompatActivity() {
    private val amb: ActivityFilmeBinding by lazy {
        ActivityFilmeBinding.inflate(layoutInflater)
    }
    private lateinit var dbReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        val filmeRecebido = intent.getParcelableExtra<Filme>(EXTRA_FILME)
        filmeRecebido?.let{ _filmeRecebido ->
            with(amb) {
                with(_filmeRecebido) {
                    nomeEt.setText(nome)
                    nomeEt.isEnabled = false
                    duracaoEt.setText(duracao)
                    anoEt.setText(anoLancamento.toString())
                    produtoraEt.setText(produtora)
                    notaEt.setText(nota.toString())
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
            var campoVazio: Int = 0
            with(amb){
                if (nomeEt.text.isEmpty()){
                    campoVazio += 1
                }
                if (duracaoEt.text.isEmpty()){
                    campoVazio += 1
                }
                if (anoEt.text.isEmpty()){
                    campoVazio += 1
                }
                if (produtoraEt.text.isEmpty()){
                    campoVazio += 1
                }
            }
            if (amb.anoEt.text.toString().toInt() < 1888){
                Toast.makeText(
                    this, "O primeiro filme criado é de 1888", Toast.LENGTH_LONG
                ).show()
            }


            else{
            if(campoVazio != 0) {
                Toast.makeText(
                    this,
                    "Existem: " + campoVazio + " campos que são obrigatórios e estão vazios",
                    Toast.LENGTH_LONG
                ).show()
            }else{
                val filme = Filme(
                    id = (filmeRecebido?.id.toString()),
                    nome = amb.nomeEt.text.toString(),
                    genero = amb.spinner1.selectedItem.toString(),
                    duracao = amb.duracaoEt.text.toString(),
                    assistido = amb.assistidoSp.selectedItem.toString(),
                    anoLancamento = amb.anoEt.text.toString().toInt(),
                    produtora = amb.produtoraEt.text.toString(),
                    nota = amb.notaEt.text.toString().toInt()
                )
                val resultIntent = Intent()
                resultIntent.putExtra(EXTRA_FILME, filme)
                setResult(RESULT_OK, resultIntent)
                finish()
            }



        }
    }

}
}