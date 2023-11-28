package com.example.gestordelibros

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

class DeleteFragment : Fragment(R.layout.fragment_delete) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_delete, container, false)
        var buttonEliminar = view.findViewById<Button>(R.id.buttonEliminar)
        var tituloIntro = view.findViewById<EditText>(R.id.tituloBorrar)
        buttonEliminar.setOnClickListener {
            val myDialog = confirmDialog(tituloIntro)
            myDialog.show(parentFragmentManager, "123")
        }
        return view
    }

}

