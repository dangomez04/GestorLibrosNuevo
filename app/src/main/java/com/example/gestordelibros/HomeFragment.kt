package com.example.gestordelibros

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private lateinit var recycler : RecyclerView
lateinit var dbHelper : SqlHelper
lateinit var db : SQLiteDatabase

 class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var listaObjetos = mutableListOf<Libro>()

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recycler = view.findViewById(R.id.recycler)

        var nuevoLibro1 = Libro(1,"La cabaña","Daniel Gómez","25-11-2023")
        var nuevoLibro2 = Libro(2,"El lago","Javier Abril","12-05-2020")
        var nuevoLibro3 = Libro(3,"La avispa","Joel Gallo","09-02-2017")
        var nuevoLibro4 = Libro(4,"El amanecer","Carlos Nieto","06-07-2023")
        var nuevoLibro5 = Libro(5,"El fin del mundo","Alejandro callejas","20-03-2021")

        listaObjetos.add(nuevoLibro1)
        listaObjetos.add(nuevoLibro2)
        listaObjetos.add(nuevoLibro3)
        listaObjetos.add(nuevoLibro4)
        listaObjetos.add(nuevoLibro5)

        recycler.layoutManager = LinearLayoutManager(requireContext())




        dbHelper = SqlHelper(requireContext())
        db = dbHelper.writableDatabase


        for(i in listaObjetos){

            val values = ContentValues().apply {
                put("titulo", i.titulo)
                put("autor", i.autor)
                put("fecha", i.fecha)

            }

            val newRowId = db?.insert("libros", null, values)

        }




        val projection = arrayOf("id", "titulo", "autor","fecha")
        val sortOrder = "id ASC"

        val cursor = db.query(
            "libros",   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            null,              // The columns for the WHERE clause
            null,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder               // The sort order
        )

        var listaBbdd = mutableListOf<Libro>()
        with(cursor) {
            while (moveToNext()) {
                var id = Integer.parseInt(getString(0))
                var titulo = getString(1)
                var autor = getString(2)
                var fecha = getString(3)
                var nuevoObjeto = Libro(id,titulo,autor,fecha)
                listaBbdd.add(nuevoObjeto)
            }

        }

        val adapter : RecyclerViewAdapter = RecyclerViewAdapter(listaBbdd)
        recycler.adapter = adapter






        return view


    }
}