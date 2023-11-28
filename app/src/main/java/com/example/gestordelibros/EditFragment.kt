package com.example.gestordelibros

import android.app.AlertDialog
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class EditFragment : Fragment() {
private lateinit var tituloIntroEditar : EditText
    lateinit var dbHelper : SqlHelper
    lateinit var db : SQLiteDatabase
    lateinit var tituloNuevoIntro : EditText
    lateinit var autorNuevoIntro : EditText
    lateinit var fechaNuevaIntro : EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit, container, false)
        //creamos dialogo
        CreateEditDialog(view)
        tituloNuevoIntro = view.findViewById(R.id.editTitulo)
        autorNuevoIntro = view.findViewById(R.id.editAutor)
        fechaNuevaIntro = view.findViewById(R.id.editFecha)
        fechaNuevaIntro.setOnClickListener{
            //mostramos dialogo
            showDatePickerDialog()

    }
        val botonEditar = view.findViewById<Button>(R.id.buttonEditar)
        botonEditar.setOnClickListener {
            dbHelper = SqlHelper(requireContext())
            db = dbHelper.writableDatabase
            val values = ContentValues().apply {
                put("titulo", tituloNuevoIntro.text.toString())
                put("autor", autorNuevoIntro.text.toString())
                put("fecha", fechaNuevaIntro.text.toString())

            }


            val selection = "titulo LIKE ?"
            val selectionArgs = arrayOf("${tituloIntroEditar.text}")
            //query para actualizar
            val count = db.update(
                "libros",
                values,
                selection,
                selectionArgs)



            if(count == 0){
                Toast.makeText(requireContext(),"El libro que has introducido no existe", Toast.LENGTH_SHORT).show()

            }else{
                parentFragmentManager?.beginTransaction()
                    ?.replace(R.id.fragmentContainerView, HomeFragment())
                    ?.commit()

                Toast.makeText(requireContext(),"${tituloIntroEditar.text} Editado con exito!", Toast.LENGTH_SHORT).show()

            }
            db.close()

        }
        return view
    }

    fun CreateEditDialog(view: View){
        val builder = AlertDialog.Builder(requireActivity())

        val inflater = requireActivity().layoutInflater
        val dialogView = inflater.inflate(R.layout.edit_dialog, null)
         tituloIntroEditar = dialogView.findViewById<EditText>(R.id.tituloEditar)
        val buttonAc = dialogView.findViewById<Button>(R.id.buttonAceptarLibro)
        val buttonCancel = dialogView.findViewById<Button>(R.id.buttonCancelarLibro)
        builder.setTitle("Editar Libro")
        builder.setView(dialogView)
        builder.setCancelable(false)
        val alertDialog = builder.create()
        var idEncontrado : Int
        var tituloEncontrado : String
        var autorEncontrado : String
        var fechaEncontrado : String

        buttonAc.setOnClickListener {
            dbHelper = SqlHelper(requireContext())
            db = dbHelper.writableDatabase


            val columnas = arrayOf("id", "titulo", "autor","fecha")
            val sortOrder = "id ASC"

            val selectionArgs = arrayOf(tituloIntroEditar.text.toString())
            //query para obtener todos los campos del libro introducido
            val cursor = db.query(
                "libros",   // The table to query
                columnas,             // The array of columns to return (pass null to get all)
                "titulo = ?",              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
            )
            with(cursor) {

                while (moveToNext()) {
                     idEncontrado = Integer.parseInt(getString(0))
                     tituloEncontrado = getString(1)
                     autorEncontrado = getString(2)
                     fechaEncontrado = getString(3)
                    //pintar datos en los EditText
                 tituloNuevoIntro.setText(tituloEncontrado)
                    autorNuevoIntro.setText(autorEncontrado)
                    fechaNuevaIntro.setText(fechaEncontrado)

                }

            }


            if(tituloIntroEditar.text.toString()==""){
                Toast.makeText(requireActivity(),"No puedes dejar el campo vacio",Toast.LENGTH_SHORT).show()
                CreateEditDialog(view)

            }else if(tituloNuevoIntro.text.toString() == "" && autorNuevoIntro.text.toString() == "" && fechaNuevaIntro.text.toString()==""){
                Toast.makeText(requireContext(),"El libro ${tituloIntroEditar.text} no existe, introduzca de nuevo un titulo", Toast.LENGTH_SHORT).show()
                CreateEditDialog(view)
            }
            cursor.close()
            db.close()

            alertDialog.dismiss()
        }

        buttonCancel.setOnClickListener {
            alertDialog.dismiss()

        }
            alertDialog.show()
    }

    private fun showDatePickerDialog() {
        val newFragment = DatePickerFragment(fechaNuevaIntro)
        newFragment.show(parentFragmentManager, "datePicker")
    }

}


