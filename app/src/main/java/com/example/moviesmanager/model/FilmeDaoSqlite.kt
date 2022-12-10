package com.example.moviesmanager.model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.moviesmanager.R
import java.sql.SQLException

class FilmeDaoSqlite (context: Context) : FilmeDao {
    companion object Constant {
        private const val FILME_DATABASE_FILE = "filmes"
        private const val FILME_TABLE = "filme"
        private const val ID_COLUMN = "id"
        private const val NOME_COLUMN = "name"
        private const val GENERO_COLUMN = "genero"
        private const val DURACAO_COLUMN = "duracao"
        private const val ASSISTIDO_COLUMN = "assistido"
        private const val ANO_COLUMN = "ano"
        private const val PRODUTORA_COLUMN = "produtora"
        private const val NOTA_COLUMN = "nota"

        private const val CREATE_FILME_TABLE_STATEMENT =
            "CREATE TABLE IF NOT EXISTS $FILME_TABLE ( " +
                    "$ID_COLUMN INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$NOME_COLUMN TEXT NOT NULL, " +
                    "$GENERO_COLUMN TEXT NOT NULL," +
                    "$DURACAO_COLUMN TEXT NOT NULL,"+
                    "$ASSISTIDO_COLUMN TEXT NOT NULL," +
                    "$ANO_COLUMN TEXT NOT NULL," +
                    "$PRODUTORA_COLUMN TEXT NOT NULL," +
                    "$NOTA_COLUMN TEXT NOT NULL );"
    }

    private var filmeSpliteDatabase: SQLiteDatabase

    init {
        filmeSpliteDatabase = context.openOrCreateDatabase(
            FILME_DATABASE_FILE,
            Context.MODE_PRIVATE,
            null
        )
        try {
            filmeSpliteDatabase.execSQL(CREATE_FILME_TABLE_STATEMENT)
        } catch (se: SQLException) {
            Log.e(context.getString(R.string.app_name), se.toString())
        }
    }

    private fun Filme.toContentValues() = with(ContentValues()) {
        put(NOME_COLUMN, nome)
        put(GENERO_COLUMN, genero)
        put(DURACAO_COLUMN, duracao)
        put(ASSISTIDO_COLUMN, assistido)
        put(ANO_COLUMN, anoLancamento)
        put(PRODUTORA_COLUMN, produtora)
        put(NOTA_COLUMN, nota)

        this
    }

    private fun contactToContentValues(filme: Filme) = with(ContentValues()) {
        put(NOME_COLUMN, filme.nome)
        put(GENERO_COLUMN, filme.genero)
        put(DURACAO_COLUMN, filme.duracao)
        put(ASSISTIDO_COLUMN, filme.assistido)
        put(ANO_COLUMN, filme.anoLancamento)
        put(PRODUTORA_COLUMN, filme.produtora)
        put(NOTA_COLUMN, filme.nota)
        this
    }

    private fun Cursor.rowToFilme() = Filme(
        getInt(getColumnIndexOrThrow(ID_COLUMN)),
        getString(getColumnIndexOrThrow(NOME_COLUMN)),
        getString(getColumnIndexOrThrow(GENERO_COLUMN)),
        getString(getColumnIndexOrThrow(DURACAO_COLUMN)),
        getString(getColumnIndexOrThrow(ASSISTIDO_COLUMN)),
        getInt(getColumnIndexOrThrow(ANO_COLUMN)),
        getString(getColumnIndexOrThrow(PRODUTORA_COLUMN)),
        getInt(getColumnIndexOrThrow(NOTA_COLUMN)),
    )


    override fun addFilme(filme: Filme) = filmeSpliteDatabase.insert(
        FILME_TABLE,
        null,
        contactToContentValues(filme)
    ).toInt()


    override fun getFilme(id: Int): Filme? {
        val cursor = filmeSpliteDatabase.rawQuery(
            "SELECT * FROM $FILME_TABLE WHERE $ID_COLUMN = ?",
            arrayOf(id.toString())
        )
        val contact = if (cursor.moveToFirst()) cursor.rowToFilme() else null

        cursor.close()
        return contact
    }

   override fun getFilmes(): MutableList<Filme> {
        val contactList = mutableListOf<Filme>()
        val cursor = filmeSpliteDatabase.rawQuery(
            "SELECT * FROM $FILME_TABLE ORDER BY $NOME_COLUMN",
            null
        )
        while (cursor.moveToNext()) {
            contactList.add(cursor.rowToFilme())
        }
        cursor.close()
        return contactList
    }

 override fun updateFilme(filme: Filme) = filmeSpliteDatabase.update(
        FILME_TABLE,
        filme.toContentValues(),
        "$ID_COLUMN = ?",
        arrayOf(filme.id.toString())
    )

    override fun deleteFilme(id: Int) =
        filmeSpliteDatabase.delete(
            FILME_TABLE,
            "$ID_COLUMN = ?",
            arrayOf(id.toString())
        )
}