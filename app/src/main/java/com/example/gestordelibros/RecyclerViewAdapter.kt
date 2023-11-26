package com.example.gestordelibros

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter (var listaObjetos : MutableList<Libro>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>()  {
    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val textTitulo : TextView = itemView.findViewById(R.id.textTitulo)
        val textAutor : TextView = itemView.findViewById(R.id.textAutor)
        val textFecha : TextView = itemView.findViewById(R.id.textFecha)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.libros, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        holder.textTitulo.text = listaObjetos[position].titulo
        holder.textAutor.text = listaObjetos[position].autor
        holder.textFecha.text = listaObjetos[position].fecha
    }

    override fun getItemCount(): Int {
        return listaObjetos.size
    }
}