package com.example.listadecompras.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

// Access property for Context
val Context.database: ListaComprasDatabase
    get() = ListaComprasDatabase.getInstace(applicationContext)


class ListaComprasDatabase(context: Context) :
    ManagedSQLiteOpenHelper(ctx = context, name = "listaCompras.db", version = 1) {

    //Padrão Singleton
    companion object {
        private var instance: ListaComprasDatabase? = null

        @Synchronized
        fun getInstace(ctx: Context): ListaComprasDatabase {
            if (instance == null) {
                instance = ListaComprasDatabase(ctx.applicationContext)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here  you create tables
        db.createTable(
            "produtos", true,
            "id" to INTEGER + PRIMARY_KEY + UNIQUE,
            "nome" to TEXT,
            "quantidade" to INTEGER,
            "valor" to REAL,
            "foto" to BLOB
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
    }

}




































