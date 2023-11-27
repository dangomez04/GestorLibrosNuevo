package com.example.gestordelibros

import android.app.DatePickerDialog
import android.content.ContentValues
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.Calendar

private lateinit var buttonAñadir : Button
private lateinit var idIntro : EditText
private lateinit var tituloIntro : EditText
private lateinit var autorIntro : EditText
private lateinit var fechaIntro : EditText

class AddFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        buttonAñadir = view.findViewById(R.id.buttonInsertar)
        idIntro = view.findViewById(R.id.idIntro)
        tituloIntro = view.findViewById(R.id.tituloIntro)
        autorIntro = view.findViewById(R.id.autorIntro)
        fechaIntro = view.findViewById(R.id.fechaIntro)


        fechaIntro.setOnClickListener {
            showDatePickerDialog()
        }

        buttonAñadir.setOnClickListener {
            val id = idIntro.text.toString().toIntOrNull() ?: 0 // Obtiene el valor del campo ID, convierte a Int o usa 0 si no es válido
            val titulo = tituloIntro.text.toString()
            val autor = autorIntro.text.toString()
            val fecha = fechaIntro.text.toString()

            val values = ContentValues().apply {
                put("id", id)
                put("titulo", titulo)
                put("autor", autor)
                put("fecha", fecha)
            }

            dbHelper = SqlHelper(requireContext())
            db = dbHelper.writableDatabase
            val newRowId = db?.insert("libros", null, values)

            if (newRowId != -1L) {
                Toast.makeText(requireContext(), "Libro agregado correctamente", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Error al agregar el libro", Toast.LENGTH_SHORT).show()
            }

        }


        return view


    }

    private fun showDatePickerDialog() {
        val newFragment = DatePickerFragment(fechaIntro)
        newFragment.show(parentFragmentManager, "datePicker")
    }

}
