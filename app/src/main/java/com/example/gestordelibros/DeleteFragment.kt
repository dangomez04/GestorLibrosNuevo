package com.example.gestordelibros

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class DeleteFragment : Fragment(R.layout.fragment_delete) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_delete, container, false)
        var buttonAñadir = view.findViewById<Button>(R.id.buttonEliminar)
        buttonAñadir.setOnClickListener {

        }
        return view
    }

}

