package com.example.moviesmanager.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import br.edu.ifsp.ads.pdm.mycontacts.adapter.FilmeAdapter
import com.example.moviesmanager.R
import com.example.moviesmanager.controller.FilmeController
import com.example.moviesmanager.databinding.ActivityMainBinding
import com.example.moviesmanager.model.Constant.EXTRA_FILME
import com.example.moviesmanager.model.Constant.VIEW_FILME
import com.example.moviesmanager.model.Filme

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val filmeController: FilmeController by lazy {
        FilmeController(this)
    }
    private val filmesList: MutableList<Filme> by lazy {
        filmeController.getFilmes()
    }
    private lateinit var filmeAdapter: FilmeAdapter

    private lateinit var carl: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        filmeAdapter = FilmeAdapter(this, filmesList)
        amb.filmesLv.adapter = filmeAdapter

        carl = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val filme = result.data?.getParcelableExtra<Filme>(EXTRA_FILME)

                filme?.let { _filme ->
                    val position = filmesList.indexOfFirst { it.id == _filme.id }
                    if (position != -1) {
                        filmesList[position] = _filme
                        filmeController.editFilme(_filme)
                    } else {
                        _filme.id = filmeController.addFilme(_filme)
                        filmesList.add(_filme)
                    }
                    filmesList.sortBy { it.nome }
                    filmeAdapter.notifyDataSetChanged()
                }
            }
        }
                registerForContextMenu(amb.filmesLv)

                amb.filmesLv.onItemClickListener =
                    AdapterView.OnItemClickListener { _, _, position, _ ->
                        val filme = filmesList[position]
                        val filmeIntent = Intent(this@MainActivity, FilmeActivity::class.java)
                        filmeIntent.putExtra(EXTRA_FILME, filme)
                        filmeIntent.putExtra(VIEW_FILME, true)
                        startActivity(filmeIntent)
                    }
            }

            override fun onCreateOptionsMenu(menu: Menu?): Boolean {
                menuInflater.inflate(R.menu.activity_menu_main, menu)
                return true
            }

            override fun onOptionsItemSelected(item: MenuItem): Boolean {
                return when(item.itemId) {
                    R.id.addFilme -> {
                        carl.launch(Intent(this, FilmeActivity::class.java))
                        true
                    }
                    R.id.ordemAlfa ->{
                        filmesList.sortedBy { it.nome }
                        filmeAdapter.notifyDataSetChanged()
                        true
                    }
                    R.id.ordemNum ->{
                        filmesList.sortedBy { it.nota }
                        filmeAdapter.notifyDataSetChanged()
                        true
                    }
                    else -> { false }
                }
            }

            override fun onCreateContextMenu(
                menu: ContextMenu?,
                v: View?,
                menuInfo: ContextMenu.ContextMenuInfo?
            ) {
                menuInflater.inflate(R.menu.activity_context_menu, menu)
            }

            override fun onContextItemSelected(item: MenuItem): Boolean {
                val position = (item.menuInfo as AdapterView.AdapterContextMenuInfo).position
                return when(item.itemId) {
                    R.id.removeMi -> {
                        filmeController.removeFilme(filmesList[position].id)
                        filmesList.removeAt(position)
                        filmeAdapter.notifyDataSetChanged()
                        true
                    }
                    R.id.editarMi -> {
                        val filme = filmesList[position]
                        val filmeIntent = Intent(this, FilmeActivity::class.java)
                        filmeIntent.putExtra(EXTRA_FILME, filme)
                        filmeIntent.putExtra(VIEW_FILME, false)
                        carl.launch(filmeIntent)
                        true
                    }
                    else -> { false }
                }
            }

    }