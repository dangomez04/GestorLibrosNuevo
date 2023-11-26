package com.example.gestordelibros

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqlHelper (context: Context) : SQLiteOpenHelper(context, "GestorLibros.db", null, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE libros (id INTEGER PRIMARY KEY, titulo TEXT, autor TEXT, fecha TEXT)")

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }



}