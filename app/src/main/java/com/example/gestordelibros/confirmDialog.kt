package com.example.gestordelibros

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class confirmDialog(var tituloBorrar : EditText) :DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(this.requireActivity())
        builder.setTitle("Confirmar")
        builder.setMessage("Seguro que deseas eliminar el libro ${tituloBorrar.text}?")

            .setPositiveButton("Aceptar") { dialog, id ->
                val dbHelper = SqlHelper(requireContext())
                val db = dbHelper.writableDatabase

                val selection = "titulo LIKE ?"
                val selectionArgs = arrayOf(tituloBorrar.text.toString())
                val deletedRows = db.delete("libros", selection, selectionArgs)
                tituloBorrar.setText("")

                if(deletedRows == 0){
                    Toast.makeText(requireActivity()," ${tituloBorrar.text.toString()} No existe",Toast.LENGTH_SHORT).show()

                }else{
                    Toast.makeText(requireActivity()," ${tituloBorrar.text.toString()} Borrado correctamente",Toast.LENGTH_SHORT).show()
                    parentFragmentManager?.beginTransaction()
                        ?.replace(R.id.fragmentContainerView, HomeFragment())
                        ?.commit()
                }
                db.close()

                dialog.dismiss()

            }
            .setNegativeButton("Cancelar") { dialog, id ->
                dialog.dismiss()

            }


        return builder.create()

    }
}